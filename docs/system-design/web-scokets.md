
---

# WebSockets
---

## 1. WebSockets – Fundamentals

### 1.1. What are WebSockets?

* WebSockets are a communication protocol that provides **full-duplex, bidirectional communication** between client and server over a **single, persistent TCP connection**.
* Unlike traditional request-response models, both client and server can **send messages independently at any time** once the connection is established.
* The connection starts as an HTTP request and is then **upgraded** to a WebSocket connection.

---

### 1.2. WebSockets vs HTTP

| Aspect               | HTTP                               | WebSockets                          |
| -------------------- | ---------------------------------- | ----------------------------------- |
| Communication        | Request → Response (client-driven) | Bidirectional (client + server)     |
| Connection           | Short-lived (stateless)            | Persistent (stateful)               |
| Overhead             | High (headers per request)         | Low (minimal framing after upgrade) |
| Real-time capability | Poor (needs workarounds)           | Native support                      |
| Use pattern          | REST APIs, CRUD                    | Streaming, real-time updates        |

**Key takeaway:**
HTTP is **pull-based**, whereas WebSockets enable **push + pull**.

---

### 1.3. Common Use Cases

* **Chat applications** (e.g., WhatsApp, Slack-like systems)
* **Online gaming** (real-time player state updates)
* **Financial dashboards** (live stock prices, crypto feeds)
* **Notifications systems** (real-time alerts)
* **Collaborative tools** (Google Docs-like editing)
* **IoT telemetry** (device ↔ server communication)

---

### 1.4. Polling vs Long-Polling vs SSE vs WebSockets

#### 1.4.1. Polling

* Client repeatedly sends requests at fixed intervals
* Server responds immediately (even if no new data)
* ❌ Inefficient (wasted requests, latency depends on interval)

---

#### 1.4.2. Long-Polling

* Client sends request → server holds it until data is available or timeout
* Once response is received, client immediately sends another request
* ✔ Better than polling
* ❌ Still HTTP overhead + not truly bidirectional

---

#### 1.4.3. Server-Sent Events (SSE)

* Server pushes updates to client over a **single HTTP connection**
* Unidirectional: **server → client only**
* Uses EventSource API in browsers
* ✔ Simpler than WebSockets for streaming updates
* ❌ No client-to-server real-time communication

---

#### 1.4.4. WebSockets

* True **full-duplex communication**
* Persistent connection after initial handshake
* Low latency, low overhead
* ✔ Best for real-time, interactive systems
* ❌ More complex to manage at scale

---

### 🧠 Interview Summary (soundbite)

* WebSockets solve the limitations of HTTP for real-time communication by enabling **persistent, bidirectional messaging with low overhead**.
* Compared to polling and long-polling, they are **more efficient and lower latency**, and unlike SSE, they support **two-way communication**.

---


## 2. WebSockets – Protocol & Mechanics

### 2.1. WebSocket Handshake

* WebSockets start as a **standard HTTP request** and then upgrade to a persistent connection.
* The client sends an HTTP request with special headers:

  * `Upgrade: websocket`
  * `Connection: Upgrade`
  * `Sec-WebSocket-Key`, `Sec-WebSocket-Version`
* The server responds with:

  * **HTTP 101 Switching Protocols**
  * Confirms upgrade and establishes the WebSocket connection

**Flow:**

1. Client → HTTP request (upgrade intent)
2. Server → `101 Switching Protocols`
3. Connection becomes a **persistent TCP socket**

**Key point:**
After the handshake, communication is no longer HTTP — it uses the WebSocket protocol.

---

### 2.2. ws:// vs wss://

* `ws://` → Plain WebSocket (no encryption)
* `wss://` → Secure WebSocket (runs over TLS, similar to HTTPS)

| Aspect   | ws://                  | wss://                           |
| -------- | ---------------------- | -------------------------------- |
| Security | No encryption          | Encrypted (TLS)                  |
| Use case | Internal networks, dev | Production, internet-facing apps |
| Port     | 80                     | 443                              |

**Interview tip:**

* Always prefer **`wss://` in production** to prevent MITM attacks and ensure data security.

---

### 2.3. Frame Structure

After the connection is established, data is exchanged in **frames**, not HTTP messages.

#### 2.3.1. Types of Frames

* **Text frames** → UTF-8 encoded data (JSON, strings)
* **Binary frames** → Raw binary (protobuf, images, etc.)
* **Control frames**:

  * **Ping** → Sent to check if the connection is alive
  * **Pong** → Response to ping
  * **Close** → Graceful shutdown signal

#### 2.3.2. Key Characteristics

* Lightweight (much smaller than HTTP headers)
* Supports **fragmentation** (large messages split into frames)
* Masking (client → server frames are masked for security)

---

### 2.4. Connection Lifecycle

#### 2.4.1. Open

* Begins with HTTP handshake and upgrade
* Once established, connection stays open

#### 2.4.2. Message Exchange

* Both client and server can send messages anytime
* No request-response restriction (true bidirectional)

#### 2.4.3. Keepalive

* Ping/Pong frames ensure connection is still active
* Helps detect broken or idle connections

#### 2.4.4. Close

* Either side can initiate closure
* Uses a **close frame** with optional status code
* TCP connection is then terminated

---

### 🧠 Interview Summary (soundbite)

* WebSockets begin with an **HTTP upgrade handshake**, then switch to a **lightweight framed protocol over a persistent TCP connection**.
* Communication happens via **text/binary frames**, with control frames like ping/pong for health checks.
* The lifecycle is simple: **open → bidirectional messaging → graceful close**.



---


## 3. WebSockets – Architecture & Scaling

### 3.1. Stateful vs Stateless Nature

* WebSockets are inherently **stateful**:

  * Each client maintains a **long-lived connection** to a specific server.
  * Server holds connection context (user session, subscriptions, etc.)
* Contrast with HTTP:

  * HTTP is **stateless** → any request can go to any server.

**Implication:**

* You can’t freely route requests across servers without considering connection state.

---

### 3.2. Horizontal Scaling Challenges

* In a scaled system (multiple servers), problems arise:

  * A client is connected to **one specific instance**
  * Other instances don’t know about that client

#### Key issues:

* **Connection affinity**: requests/messages must reach the same server
* **State sharing**: how do other nodes communicate with that connection?
* **Rebalancing difficulty**: moving live connections between servers is hard

---

### 3.3. Load Balancing Strategies

#### 3.3.1. Layer 4 (Transport-level)

* Works at TCP level (IP + port)
* Pros:

  * Faster, less overhead
  * Good for raw WebSockets
* Cons:

  * No application-level routing intelligence

---

#### 3.3.2. Layer 7 (Application-level)

* Works at HTTP/WebSocket protocol level
* Can inspect headers, cookies, paths
* Pros:

  * Smarter routing (auth-based, path-based)
* Cons:

  * Slightly higher latency/complexity

---

#### 3.3.3. Sticky Sessions (Session Affinity)

* Ensures a client always hits the **same backend server**
* Techniques:

  * Cookie-based affinity
  * IP-hash routing

**Trade-off:**

* ✔ Simpler architecture
* ❌ Can cause uneven load distribution

---

### 3.4. Using Message Brokers for Scaling

To decouple servers and share events:

#### 3.4.1. Pattern:

* Each WebSocket server handles its own client connections
* Backend services publish events to a **message broker**
* All WebSocket servers subscribe and forward messages to relevant clients

#### 3.4.2. Common choices:

* Redis (Pub/Sub, low latency)
* Apache Kafka (high throughput, durable streams)

#### 3.4.3. Benefits:

* Enables **horizontal scaling**
* Decouples producers from WebSocket nodes
* Supports multi-instance broadcasting

---

### 3.5. Fan-out Patterns (Broadcasting)

Fan-out = sending one message to many clients

#### 3.5.1. Types:

* **1 → 1**: direct messaging (chat)
* **1 → many**: room/channel broadcast (chat room, live stream)
* **many → many**: collaborative systems

#### 3.5.2. Implementation Approaches:

* In-memory (single node) → simple but not scalable
* Broker-based fan-out → scalable across nodes
* Hierarchical fan-out → reduce duplication at scale

#### 3.5.3. Challenges:

* Efficiently tracking **subscriptions (who listens to what)**
* Avoiding duplicate deliveries
* Handling millions of concurrent connections

---

### 🧠 Interview Summary (soundbite)

* WebSockets introduce **stateful, long-lived connections**, which complicate horizontal scaling.
* Scaling requires **connection affinity + external coordination (e.g., Redis/Kafka)**.
* Real-time systems rely on **fan-out patterns and pub/sub architectures** to broadcast messages across distributed nodes.


---


## 4. WebSockets – Reliability & Performance

### 4.1. Handling Dropped Connections & Reconnection

* WebSocket connections can drop due to:

  * Network instability
  * Mobile app backgrounding
  * Server restarts or deploys
  * Idle timeouts (proxies, load balancers)

#### 4.1.1. Reconnection Strategies

* Clients should automatically reconnect when disconnected
* Use **exponential backoff**:

  * Retry after increasing intervals (e.g., 1s → 2s → 4s → 8s…)
* Add **jitter** (random delay) to avoid thundering herd problem
* Resume state if possible:

  * Rejoin rooms
  * Fetch missed messages (via REST fallback or event replay)

**Key idea:**
Design assuming **connections are unreliable by default**.

---

### 4.2. Heartbeats (Ping/Pong)

* Used to detect **dead or stale connections**
* Mechanism:

  * Server sends **ping**
  * Client responds with **pong** (or vice versa)
* Helps:

  * Identify broken TCP connections
  * Keep connection alive through intermediaries (proxies)

#### 4.2.1. Best Practices

* Regular interval (e.g., 20–30 seconds)
* Close connection if no pong received within timeout
* Clean up resources for dead clients

---

### 4.3. Backpressure Handling (Slow Clients)

* Problem:

  * Server produces messages faster than client can consume
  * Leads to **buffer buildup → memory pressure → crashes**

#### 4.3.1. Strategies:

* **Buffer limits**:

  * Cap outgoing message queue per client
* **Drop policies**:

  * Drop oldest messages (for real-time feeds)
  * Drop client entirely if too slow
* **Flow control**:

  * Pause sending when client is overwhelmed
* **Message prioritization**:

  * Critical vs non-critical updates

**Example:**
In stock price streaming, you may drop intermediate updates and only send the latest value.

---

### 4.4. Rate Limiting & Throttling

* Prevent abuse and protect system stability

#### 4.4.1. Types:

* **Connection limits** per IP/user
* **Message rate limits** (messages/sec per client)
* **Global throughput limits**

#### 🔸 Techniques:

* Token bucket / leaky bucket algorithms
* Server-side enforcement
* Gateway-level throttling (API gateway / LB)

**Why it matters:**
WebSockets can be abused for **message flooding or DoS attacks**.

---

### 4.5. Memory & Connection Limits per Server

* Each connection consumes:

  * File descriptor
  * Memory (buffers, metadata)
  * CPU (event loop handling)

#### 4.5.1. Constraints:

* OS limits (e.g., max open file descriptors)
* Runtime limits (Node.js, JVM thread/memory constraints)

#### 4.5.2. Optimization Strategies:

* Use **event-driven, non-blocking servers** (e.g., epoll-based)
* Tune OS settings (ulimit, TCP stack)
* Keep message payloads small
* Use binary formats where possible

#### 4.5.3. Capacity Planning:

* Estimate:

  * Memory per connection
  * Messages per second
* Example:

  * 50K–1M concurrent connections per node (depending on stack and tuning)

---

### 🧠 Interview Summary (soundbite)

* WebSocket systems must handle **unreliable networks, slow clients, and resource limits**.
* Key techniques include **reconnection with backoff, heartbeat-based health checks, backpressure control, and rate limiting**.
* At scale, performance depends heavily on **efficient resource management and connection handling**.


---


## 5. WebSockets – Security

### 5.1. Authentication Strategies

* WebSockets don’t have built-in auth → must be handled during or after handshake

#### 🔸 Common Approaches:

* **JWT (JSON Web Token)**:

  * Sent in query params or headers during handshake
  * Server validates before upgrading connection
* **Cookies (session-based auth)**:

  * Works well with existing web sessions
  * Automatically sent by browser
* **Token-based (custom headers / query params)**:

  * Often used in mobile or microservices

#### 5.1.2. Key Considerations:

* Authenticate **at connection time**
* Optionally revalidate periodically for long-lived connections
* Avoid sending sensitive tokens in plain `ws://`

---

### 5.2. Authorization (Per Message / Channel)

* Authentication ≠ Authorization

#### 5.2.1. Patterns:

* **Channel-based authorization**:

  * User can only subscribe to allowed topics (e.g., chat room, stock feed)
* **Message-level checks**:

  * Validate every incoming action (send message, join room, etc.)

#### 5.2.2. Example:

* User authenticated ✔
* But cannot:

  * Join unauthorized chat room ❌
  * Publish to restricted topic ❌

**Best practice:**

* Never trust client → enforce authorization on **every action**

---

### 5.3. TLS (wss://) Usage

* Always use **`wss://` (WebSocket over TLS)** in production

#### 5.3.1. Why:

* Encrypts data in transit
* Prevents **Man-in-the-Middle (MITM)** attacks
* Ensures secure authentication token exchange

#### 5.3.2. Notes:

* Same security guarantees as HTTPS
* Required for most modern browsers in secure contexts

---

### 5.4. Preventing Common Attacks

#### 5.4.1. Connection Flooding (DoS)

* Attacker opens many WebSocket connections

**Mitigation:**

* Limit connections per IP/user
* Use load balancers + firewalls
* Enforce authentication early

---

#### 5.4.2. Message Flooding

* Excessive messages sent over a valid connection

**Mitigation:**

* Rate limiting (messages/sec)
* Drop or throttle abusive clients

---

#### 5.4.3. Payload Attacks

* Large or malformed messages

**Mitigation:**

* Enforce max message size
* Validate schema (JSON structure, types)

---

#### 5.4.4. Idle Connection Abuse

* Open connections without activity

**Mitigation:**

* Use heartbeats (ping/pong)
* Close inactive connections

---

### 5.5. CORS & Origin Checks

* WebSockets are **not fully governed by standard CORS rules**, but:

#### 5.5.1. Origin Header:

* Browser sends an `Origin` header during handshake
* Server should validate it

#### 🔸 Why it matters:

* Prevents **Cross-Site WebSocket Hijacking (CSWSH)**

#### 🔸 Best Practices:

* Whitelist trusted origins
* Reject unknown or suspicious origins
* Combine with authentication (don’t rely on origin alone)

---

### 🧠 Interview Summary (soundbite)

* WebSocket security relies on **strong authentication at handshake, strict authorization per action, and encrypted communication via `wss://`**.
* Systems must guard against **connection flooding, message abuse, and unauthorized access**, while validating origins to prevent cross-site attacks.

---


## 6. WebSockets – Implementation Knowledge

### 6.1. Libraries / Frameworks

#### 6.1.1. Native WebSocket APIs

* Browser provides built-in `WebSocket` API
* Backend examples:

  * Node.js → `ws` library
  * Java → Spring WebSocket
  * Go → Gorilla WebSocket

**Pros:**

* Lightweight, minimal overhead
* Full control over protocol and behavior

**Cons:**

* You must implement:

  * Reconnection logic
  * Heartbeats
  * Message routing
  * Scaling patterns

---

#### 6.1.2. Abstraction Libraries (e.g., Socket.IO)

* Socket.IO provides a higher-level abstraction over WebSockets

**Features:**

* Automatic reconnection
* Fallbacks (polling if WebSockets unavailable)
* Rooms/channels abstraction
* Built-in event system

**Trade-off:**

* ✔ Faster to build
* ❌ Extra overhead + not pure WebSocket protocol

---

### 6.2. Raw WebSockets vs Abstractions

| Aspect        | Raw WebSockets         | Socket.IO (or similar)              |
| ------------- | ---------------------- | ----------------------------------- |
| Protocol      | Standard WebSocket     | Custom protocol on top              |
| Control       | Full control           | Abstracted                          |
| Performance   | Higher (less overhead) | Slightly lower                      |
| Features      | Manual implementation  | Built-in (rooms, retries, fallback) |
| Compatibility | Depends on environment | Better (fallback support)           |

**Interview insight:**

* Use **raw WebSockets** for high-performance, low-latency systems
* Use **Socket.IO** (or similar) for faster development and richer features

---

### 6.3. Handling Binary Data

#### 6.3.1. JSON (Text Frames)

* Most common format
* Human-readable, easy to debug

**Cons:**

* Larger payload size
* Slower serialization/deserialization

---

#### 6.3.2. Binary Formats

* Examples:

  * Protocol Buffers (Protobuf)
  * MessagePack
  * Avro

**Pros:**

* Smaller payloads (better bandwidth usage)
* Faster parsing
* Better for high-throughput systems

**Cons:**

* More complex
* Requires schema management

**When to use:**

* Real-time systems with **high frequency + large scale** (e.g., trading systems, multiplayer games)

---

### 6.4. Versioning & Backward Compatibility

* WebSocket connections are long-lived → version mismatches are common

#### 6.4.1. Challenges:

* Clients may run older versions
* Server deploys can introduce breaking changes

---

#### 🔸 Strategies:

* **Versioned message formats**

  * Include version field in payload
* **Backward-compatible schema changes**

  * Additive changes only (avoid breaking fields)
* **Graceful degradation**

  * Ignore unknown fields
* **Rolling deployments**

  * Ensure old + new versions coexist safely

---

#### 6.4.3. Contract Management:

* Treat WebSocket messages like APIs
* Use schema validation (e.g., JSON schema, protobuf schema)

---

### 🧠 Interview Summary (soundbite)

* Implementation choices involve trade-offs between **control vs convenience**:

  * Raw WebSockets → performance and flexibility
  * Abstractions like Socket.IO → faster development with built-in features
* Efficient systems often use **binary formats** and enforce **strict versioning strategies** to maintain compatibility at scale.


---


## 7. WebSockets – Observability & Debugging

### 7.1. Logging (Connection Events & Message Flow)

#### 7.1.1. What to Log:

* **Connection lifecycle events**:

  * Connect, disconnect, reconnect, errors
* **Authentication/authorization events**
* **Subscription changes** (join/leave channels, rooms)
* **Message flow (selectively)**:

  * Incoming/outgoing events (avoid logging full payloads at scale)

#### 🔸 Best Practices:

* Use **structured logging** (JSON logs)
* Include:

  * connection ID
  * user ID
  * correlation/request ID
* Avoid logging sensitive data (tokens, PII)

**Goal:**
Be able to trace *“what happened to this user’s connection?”*

---

### 7.2. Metrics (Key Signals to Monitor)

#### 7.2.1. Core Metrics:

* **Active connections** (current open sockets)
* **Connection rate** (new connections/sec, disconnects/sec)
* **Message rate**:

  * Incoming messages/sec
  * Outgoing messages/sec
* **Latency**:

  * End-to-end message delivery time
* **Error rates**:

  * Failed auth, dropped connections, message failures

---

#### 7.2.2. System Metrics:

* CPU, memory usage
* Event loop lag (Node.js)
* Network I/O throughput

---

#### 7.2.3. Why it matters:

* Helps detect:

  * Traffic spikes
  * Bottlenecks
  * Slow consumers
  * System overload

---

### 7.3. Debugging Tools

#### 7.3.1. Browser DevTools

* Inspect WebSocket connections in **Network tab**
* View:

  * Frames (messages)
  * Payloads
  * Connection status

---

#### 7.3.2. Packet-Level Tools

* Wireshark

  * Deep inspection of WebSocket frames over TCP
  * Useful for low-level debugging

---

#### 7.3.3. Server-side Debugging

* Connection dumps (active clients)
* Replay logs
* Feature flags for verbose logging

---

### 7.4. Distributed Tracing in Real-Time Systems

#### 7.4.1. Problem:

* WebSockets are long-lived → not simple request-response
* Harder to trace compared to HTTP APIs

---

#### 7.4.2. Approach:

* Assign a **connection ID** per socket
* Attach **trace IDs** to messages/events
* Propagate context across services

---

#### 7.4.3. Tools/Concepts:

* OpenTelemetry-style tracing
* Correlating:

  * Client action → WebSocket message → backend service → response

---

#### 7.4.4. Example Flow:

1. User sends message via WebSocket
2. Message carries trace ID
3. Backend processes it (via Kafka/Redis)
4. Response is traced back to originating connection

---

### 🧠 Interview Summary (soundbite)

* Observability in WebSockets requires tracking **connections, message flows, and system health in real time**.
* Key pillars are **structured logging, high-cardinality metrics, and distributed tracing**, since traditional request-response debugging doesn’t apply to persistent connections.


---

## 8. WebSockets – System Design Perspective

### 8.1. Designing Real-Time Systems

#### 8.1.1. Chat Systems

* Requirements:

  * Low latency message delivery
  * Ordering (per conversation)
  * Presence (online/offline)
* Design patterns:

  * WebSocket for real-time delivery
  * Message queue (fan-out)
  * Persistent storage for history
* Challenges:

  * Message ordering across distributed systems
  * Handling offline users (store + forward)

---

#### 8.1.2. Live Dashboards (e.g., stock prices)

* Requirements:

  * High-frequency updates
  * Massive fan-out (1 → many)
* Design patterns:

  * Pub/Sub system (broadcast updates)
  * Drop stale updates (only latest matters)
* Optimization:

  * Use aggregation + throttling (don’t send every tick)

---

#### 8.1.3. Multiplayer Systems

* Requirements:

  * Ultra-low latency
  * Frequent state updates
* Design patterns:

  * Region-based servers (players connect to nearest server)
  * Delta updates (send only changes, not full state)
* Trade-off:

  * Consistency vs latency (eventual consistency often acceptable)

---

### 8.2. Trade-offs: WebSockets vs REST vs SSE

| Feature       | WebSockets        | REST               | SSE               |
| ------------- | ----------------- | ------------------ | ----------------- |
| Communication | Bidirectional     | Request-response   | Server → Client   |
| Latency       | Low               | Higher             | Low               |
| Scalability   | Harder (stateful) | Easier (stateless) | Moderate          |
| Complexity    | High              | Low                | Medium            |
| Use case      | Real-time apps    | CRUD APIs          | Streaming updates |

#### 8.2.1. When to Use What:

* **WebSockets** → interactive, real-time (chat, gaming)
* **REST** → standard APIs, non-real-time operations
* **SSE** → simple server push (notifications, feeds)

---

### 8.3. Cost Considerations

#### 8.3.1. WebSockets:

* Long-lived connections → consume:

  * Memory per connection
  * File descriptors
  * Persistent compute resources
* Requires always-on infrastructure

---

#### 8.3.2. REST:

* Stateless → better resource utilization
* Scales easily with autoscaling

---

#### 8.3.3. Cost Trade-off:

* WebSockets:

  * ✔ Lower latency, better UX
  * ❌ Higher infra cost at scale
* REST:

  * ✔ Cheaper, simpler
  * ❌ Not real-time

**Optimization strategies:**

* Use WebSockets **only where needed**
* Combine with REST (hybrid architecture)

---

### 8.4. Multi-Region Deployment & Latency Optimization

#### 8.4.1. Challenges:

* Latency increases with distance
* Maintaining state across regions is complex

---

#### 🔸 Strategies:

**1. Geo-routing**

* Route users to nearest region (via DNS / edge routing)

---

**2. Regional WebSocket Gateways**

* Each region handles its own connections
* Backend systems sync via global message bus

---

**3. Data Replication**

* Use distributed systems (e.g., replicated logs, caches)
* Ensure eventual consistency across regions

---

**4. Edge Optimization**

* Use CDNs / edge networks for faster handshake
* Terminate TLS closer to users

---

#### 🔸 Trade-offs:

* Strong consistency vs low latency
* Cross-region communication cost

---

### 🧠 Interview Summary (soundbite)

* WebSockets are ideal for **low-latency, real-time systems**, but introduce **statefulness and scaling complexity**.
* Strong system design involves **hybrid architectures (WebSockets + REST + pub/sub)** and careful handling of **fan-out, cost, and multi-region latency trade-offs**.

---

Here’s a strong, practical note for **Edge Cases & Production Concerns in WebSockets**—this is where real-world experience shows:

---

### 9.1. Network Partitions & Intermittent Connectivity

* Common in:

  * Mobile networks (switching WiFi ↔ cellular)
  * Unstable internet conditions
  * Cross-region latency spikes

#### 9.1.1. Problems:

* Silent disconnects (connection appears open but is dead)
* Message loss or duplication
* Out-of-order delivery after reconnection

#### 🔸 Strategies:

* **Heartbeat (ping/pong)** to detect stale connections
* **Client reconnection with exponential backoff + jitter**
* **Message acknowledgements (ACKs)**:

  * Ensure delivery guarantees (at-least-once)
* **Replay mechanism**:

  * Fetch missed messages after reconnect (via REST or event log)

**Key idea:**
Design for **eventual recovery, not perfect connectivity**

---

### 9.2. Graceful Shutdown (Connection Draining)

* Problem:

  * During deployments or scaling down, active connections get dropped abruptly

#### 9.2.1. Best Practice: Draining

1. Mark server as **unavailable for new connections**
2. Keep existing connections alive
3. Notify clients (optional: “server restarting” event)
4. Allow time for:

   * Clients to reconnect elsewhere
   * In-flight messages to complete
5. Force close after timeout

---

#### 9.2.2. Techniques:

* Load balancer removes node from rotation
* Use **connection draining window** (e.g., 30–120 seconds)
* Kubernetes:

  * `preStop` hooks
  * Readiness probes

---

### 9.3. Version Mismatches (Client vs Server)

* Reality:

  * Clients (especially mobile apps) may run **older versions**
  * Server gets updated frequently

#### 9.3.1. Problems:

* Breaking message formats
* Unsupported events/actions
* Unexpected failures

---

#### 🔸 Strategies:

* **Versioned message schema**

  * Include version field in payload
* **Backward compatibility**:

  * Additive changes only
* **Feature flags**:

  * Enable/disable features per client version
* **Graceful fallback**:

  * Ignore unknown fields/events

---

### 9.4. Fallback Mechanisms (When WebSockets Are Blocked)

* Issue:

  * Some environments (corporate proxies, firewalls) block WebSockets

#### 9.4.1. Fallback Options:

* **Long-polling**
* **HTTP polling**
* **Server-Sent Events (SSE)**

---

#### 9.4.2. Common Approach:

* Libraries like Socket.IO:

  * Automatically fall back to polling if WebSockets fail

---

#### 9.4.3. Detection:

* Connection upgrade fails → fallback triggered
* Timeout-based fallback

---

#### 🔸 Trade-offs:

* ✔ Improved compatibility
* ❌ Higher latency and overhead

---

### 🧠 Interview Summary (soundbite)

* Production WebSocket systems must handle **unreliable networks, deployments, and heterogeneous clients**.
* Key techniques include **reconnection + replay, graceful connection draining, backward-compatible protocols, and fallback mechanisms when WebSockets are unavailable**.

---



















