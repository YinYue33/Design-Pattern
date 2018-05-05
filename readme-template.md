# Pattern

## Motivation

## Problem

## Goal

## Solution

## Definition
The Command Pattern encapsulates a request as an object, thereby letting you parameterize other objects with different requests, queue or log requests(see below), and support undoable operations.

## Other Use cases
1. Queuing requests: add commands to the queue on one end, and on the other end sit a group of threads. Threads remove a command from the queue, call its execute() method,wait for the call to finish, then discard the command object and retrieve a new one
2. Logging requests: save all the operations(commands) since the last check point, and if there is a system failure, apply those operations to the checkpoint. e.g. a spreadsheet application: implement failure recovery by logging the actions every time a change occurs. 
3. sets of operations in a transactional manner: all of the operations complete, or none of them do