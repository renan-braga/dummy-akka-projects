# Dummy Akka Projects

This repository contains a collection of simple experimental projects using **Akka**, created with the sole purpose of learning and exploring Akka's core concepts for building concurrent, distributed, and reactive systems.

## Objective

The main goal of this repository is to **learn more about Akka**, through hands-on coding and small examples that cover:

- Akka Typed Actor Model
- Message-based communication
- Immutable state management
- Potential integration with other Akka modules (e.g., Akka Streams, Akka HTTP)

These projects are not meant to be production-ready apps, but rather educational exercises to help understand how Akka works in practice.

## Contents

Inside this repository you'll find:

- **Sample Projects:**
  - **Counter Actor:** A basic actor example that maintains a counter, handles increment messages, and returns the current count.
  - *(More examples may be added as learning progresses)*

- **Experimental Approaches:**
  Trying out different ways of using Akka features, including potential future explorations of Streams, HTTP, or Clustering.

## Technologies & Dependencies

- **Scala:** Version 2.13 (or whatever is defined in your `build.sbt`)
- **Akka Actor Typed:** Core module used for building typed actors

Example dependency in `build.sbt`:

```scala
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.8.5"
