# Emergency Notification System

> This system is designed for fast and efficient notification of a large number of recipients in emergency situations.

## Functional Requirements:

- [x] **Notification Sending:** Clients can send notifications to registered recipients through the following communication channels:
    - [x] Email
    - [ ] Push notifications
    - [x] SMS
- [x] **Notification Templates:** Clients can create and manage predefined notification templates for quick message sending.
- [x] **Recipient Responses:** Recipients can respond to notifications, providing their safety status or other relevant information (e.g., confirming they are safe and in a shelter).

## Non-Functional Requirements:

- [x] **High Availability:** The system should be available and operational even during peak loads or in case of system failures.
- [x] **Reliability:** The system ensures stable delivery of notifications without data loss or delays.
- [x] **Low Latency:** The system provides fast notification sending and reception, minimizing response time during emergencies.
- [x] **Scalability:** The system can handle an increasing number of recipients and notifications without compromising performance or functionality.
- [x] **Security:** The system includes security measures to protect sensitive information, prevent unauthorized access, and ensure data integrity.

## Additional Features:

- **Email Update Guarantee:** When registering a recipient with an existing email address in the system, the recipient's information is updated with the new registration details. If multiple identical email addresses exist in the .xlsx file, the last occurrence will overwrite previous ones.

## Project Architecture Diagram

![Emergency Notification System Diagram excalidraw](https://github.com/user-attachments/assets/417b4e09-9a89-4784-be25-c824e07a38ce)
