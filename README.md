# EduHappy-Core-SaaS
Enterprise J2EE School Management SaaS Platform (LMS, ERP, SIMS and SMS)
# EduHappy Core SaaS Platform

EduHappy is a multi-tenant, enterprise-grade School Management Software (SaaS) platform comprising over 20,000 lines of core production code. Designed and architected from the ground up, the platform unifies four mission-critical educational and administrative modules into a single ecosystem.

## 🏗️ Core System Modules

* **Learning Management System (LMS):** Course creation, assignment distribution, digital grading, and student progress tracking.
* **Student Information Management System (SIMS):** Enrollment lifecycles, attendance monitoring, academic transcripts, and parent/student portals.
* **School ERP:** Chart of accounts, payroll management, employee allowances, tuition fee structures, and financial reporting.
* **School Administration:** Role-based access control (RBAC), institutional configuration, and automated operational workflows.

## ⚙️ Technical Architecture & Stack

Built using pure **Java 7 / J2EE** enterprise standards, the application avoids heavy third-party framework abstractions in favor of direct lifecycle and state control:

* **Backend Controllers:** Custom HTTP **Servlets** (`src/controllers`) handling request routing, session validation, and business logic execution.
* **Data & State Layer:** Classic **JavaBeans** and **POJOs** (`src/`) managing structured data transfer and encapsulation.
* **Presentation Layer:** Dynamic views built with **JSP**, **JSTL**, **HTML**, and **JavaScript** (`WebContent/`).
* **Database:** Relational schema hosted on **MySQL** with JDBC connection pooling.

## ☁️ Production Cloud Infrastructure (AWS)

The platform was engineered for high availability and horizontal scalability on Amazon Web Services:

* **Compute & Scaling:** Deployed across **Amazon EC2** instances utilizing custom AMIs, **Auto Scaling** groups, and **Elastic Load Balancing (ELB)** to handle peak academic traffic.
* **Storage & Database:** Persistent relational storage on **Amazon RDS**, paired with **Amazon EBS** volumes and **Amazon S3** for static assets and document storage.
* **Networking & Security:** Traffic routing via **Amazon Route 53**, strict **Security Groups**, Elastic IPs, and granular access policies managed through **AWS IAM**.

## 👨‍💻 Author & Credentials

Architected and developed by **Hossam Amin Elmahdy**
* Oracle Certified Expert, Java Web Component Development (OCEJWCD)
* Oracle Certified Professional, Java Programmer (OCPJP)
* Oracle Certified Associate, Java Programmer (OCAJP)
