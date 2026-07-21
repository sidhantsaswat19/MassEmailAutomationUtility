# 📧 Mass Email Automation Utility

A lightweight, high-performance Java application designed to automate the distribution of bulk, individualized emails. 

Unlike standard mail-merge tools or BCC-based scripts that often trigger spam filters, this utility programmatically generates 1-on-1 emails customized for each recipient. It dynamically reads contact data from a CSV file, constructs multipart MIME envelopes containing both a personalized message and a binary attachment (e.g., `.docx`), and dispatches them through a standard SMTP server.

## ✨ Key Features

* **True 1-on-1 Delivery:** Eliminates the use of BCC, ensuring emails land in primary inboxes and protecting recipient privacy.
* **Network Optimization:** Prevents IP rate-limiting by establishing a single, persistent TCP connection to the SMTP server, rather than opening and closing a connection for every iteration of the sending loop.
* **Dynamic File I/O:** Parses external `.csv` files for recipient details on the fly.
* **Multipart Envelopes:** Handles complex MIME types to securely attach Word documents (`.docx`), PDFs, or other binaries alongside the text body.
* **Portable Deployment:** Packaged as a standalone "Fat JAR" via Maven, requiring no IDE to run on the host machine.

## 🛠️ Technology Stack

* **Language:** Java 21
* **Build Tool:** Maven
* **Core Libraries:** Jakarta Mail API, Eclipse Angus (Implementation)
* **Protocols:** SMTP, TLS/SSL

## 🚀 How to Run Locally

### Prerequisites
* Java Runtime Environment (JRE) 11 or higher installed on your machine.
* A valid SMTP account (e.g., Gmail) with an **App Password** generated (standard passwords will not work due to modern security policies).

### Setup Instructions
1. Clone the repository:
   ```bash
   git clone [https://github.com/sidhantsaswat19/mass-email-utility](https://github.com/sidhantsaswat19/mass-email-utility)
