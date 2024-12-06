# Project Report: Email System

## Overview:
This project is the implementation of an email system as part of an advanced programming techniques course. The system is designed to manage user creation, sending and receiving messages, and organizing messages into different storage types (in-memory and file-based). Additionally, design patterns are applied to ensure the system is modular, extensible, and maintainable. 

## Technologies Used:
1. **Java**: The primary programming language used to implement the core logic of the email system. Java is chosen for its robustness, scalability, and extensive ecosystem.
   
2. **Scala**: Used in specific components, such as message censorship and the account/domain hierarchy. Scala's functional programming features are utilized for handling operations like recursion and list manipulation in an elegant and concise manner.

3. **Redis**: Used for persistent message storage, leveraging Redis’s fast in-memory data store capabilities. Redis enhances the system’s performance, especially in real-time email retrieval scenarios.

4. **CLI (Command Line Interface)**: A simple interface that allows users and administrators to interact with the email system. Through the CLI, users can perform actions like creating accounts, sending messages, and browsing emails.

5. **JDBC (if used for database integration)**: JDBC can be integrated into the system for database connectivity, enabling the system to manage emails and user data in a relational database for more persistent storage solutions.

## Design Patterns Implemented:
### 1. **Factory Pattern**:
The Factory pattern is employed to abstract the creation of various objects, such as different types of `MailStore` objects (e.g., in-memory or file-based storage). This allows the system to choose the appropriate storage type dynamically without altering the core logic. The Factory pattern ensures the system is flexible and can easily accommodate new types of storage in the future.

### 2. **Adapter Pattern**:
The Adapter pattern is used to integrate different storage types (e.g., Redis, file-based storage) into a common interface that the rest of the system interacts with. This allows the system to handle different kinds of storage seamlessly, making it extensible without changing the core mail-handling logic. For example, the `MailStore` interface ensures the system works with multiple storage mechanisms without knowing the specifics of each one.

### 3. **Decorator Pattern**:
The Decorator pattern is utilized to add new functionality (such as censorship) to messages without modifying their original structure. For instance, the `Censor` class can be applied to modify the content of a message (e.g., replacing certain words with "CENSORED") before it is delivered or stored, all while preserving the original message format and behavior.

### 4. **Dynamic Proxy Pattern**:
This pattern is used to dynamically create objects that represent interactions with the email system, such as creating new user accounts or mailboxes. The use of dynamic proxies helps in reducing boilerplate code and improves flexibility in creating new objects without knowing their exact class at compile time. This is particularly useful for interactions with users or domains that may not be known in advance.

### 5. **Strategy Pattern**:
The Strategy pattern is used to determine how the censorship of messages is applied. Depending on the needs of the system, the strategy can change between stack-based recursion or tail-recursion to process and censor messages. The flexibility offered by the Strategy pattern allows the system to adjust the approach dynamically without altering the underlying message processing logic.

### 6. **Visitor Pattern**:
The Visitor pattern allows new operations to be added to existing object structures without modifying their classes. In this system, the `Visitor` pattern is used to apply operations like traversing and modifying the `AComponent` hierarchy (which includes `Account` and `Domain` classes). This makes it easy to extend the system with new features, such as searching for specific emails or applying filters to users and domains, without modifying their code.

## Key Components:
### 1. **MailService**:
The `MailService` class manages the list of mailboxes. It provides functionality for adding, removing, and searching for mailboxes associated with users. This component is at the core of the email system, handling the sending, receiving, and storage of messages. It interacts with different storage types through the `MailStore` interface.

### 2. **MailStore**:
`MailStore` is an abstract class that defines the interface for different types of message storage. It allows the email system to work with various storage backends (e.g., in-memory, file-based storage, or Redis) without changing the core mail management logic. Concrete implementations of `MailStore` include classes like `MailStoreOnFile`, which stores messages in a file, and other types like in-memory storage or Redis storage.

### 3. **MailStoreOnFile**:
This class implements the `MailStore` interface and handles the storage of messages in a file system. It enables persistent storage for email messages by writing and reading them from a file. This approach is particularly useful for low-cost or simple persistence where real-time access speed is not critical.

### 4. **CLI (Command Line Interface)**:
The `CLI` provides a simple and intuitive interface for interacting with the system. It allows administrators and users to perform various actions such as creating new accounts, sending messages, viewing received emails, and applying censorship or other operations. The CLI acts as the user-facing component that communicates with the backend email system.

### 5. **Message**:
The `Message` class represents an individual email. It contains attributes such as sender, receiver, subject, body, and date. The class may be extended to add additional features such as message priority or read/unread status. The `Message` class is used by other components, like `MailStore`, to store and retrieve email content.

### 6. **Censor**:
The `Censor` object is responsible for processing messages and censoring words from the email content. There are two methods implemented for censorship: a stack-based recursion (`stack_censor`) and a tail-recursive method (`tail_censor`). Both methods ensure that specific words (from a predefined list) are replaced with "CENSORED" in the email body. The `splitMessage` method performs the word replacement.

### 7. **AComponent, Account, and Domain**:
The `AComponent` class is an abstract class that represents a general component in the system. It can be a user (`Account`) or a domain (`Domain`). The `Account` class represents individual email accounts, while the `Domain` class represents email domains that may contain multiple accounts. The `AComponent` class facilitates the handling of hierarchical structures in the system, allowing domains to contain multiple accounts and operations to be applied to them collectively.

   - **Account**: Represents an individual user in the email system. It is responsible for managing the mailbox and interacting with the mail service to send and receive messages.
   - **Domain**: Represents a collection of accounts within a specific domain. A domain can contain multiple users and supports hierarchical management, making it suitable for organizational email systems.

### 8. **Comparators**:
The project includes several comparators to sort and manage emails based on different criteria:
   - **DateComparator**: Sorts messages by the date they were sent or received, allowing users to view emails in chronological order.
   - **ReceiverComparator**: Sorts messages by the recipient, making it easier to find messages sent to specific individuals.
   - **SenderComparator**: Sorts messages by the sender, which is useful for grouping emails by the person who sent them.

## System Workflow:
1. **User Creation**: Users are created using the CLI, which assigns them a mailbox and associates them with a specific domain.
2. **Message Sending and Receiving**: Once an account is created, users can send and receive messages. The messages are stored in the corresponding `MailStore` (in-memory, file, or Redis).
3. **Message Censorship**: If the system detects certain words in the message body that need to be censored, it processes the message using the `Censor` object, replacing sensitive words with the word "CENSORED."
4. **Account and Domain Management**: Domains are responsible for managing groups of users. Operations such as adding or removing users are performed at the domain level.
5. **Email Sorting**: Users can sort their messages by different criteria, such as sender, receiver, or date, using the implemented comparators.

## Conclusion:
This email system project demonstrates a comprehensive approach to building a modular, extensible, and efficient system for managing email messages. By applying key design patterns such as Factory, Adapter, Decorator, Dynamic Proxy, Strategy, and Visitor, the system is flexible and easily extendable. The use of different storage mechanisms like Redis and file storage ensures that the system can be adapted to different needs, whether for real-time performance or long-term persistence.
