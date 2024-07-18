# Fruit of the Loom: Exploring Project Loom and Virtual Threads

## Examples

### Non-Blocking

This example shows what happens when you execute a `Runnable` which doesn't perform a blocking operation in a `VirtualThread`.

```shell
mvn -Pnon-blocking compile exec:java
```

You should see output similar to the following:

```shell
[INFO] --- exec:3.3.0:java (default-cli) @ vthreads ---
16:47:56.045 [chad] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#41,chad]/runnable@ForkJoinPool-1-worker-3: entering an infinite loop...
16:47:56.045 [bob] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#38,bob]/runnable@ForkJoinPool-1-worker-2: sleeping for 1 second...
16:47:56.045 [alice] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#36,alice]/runnable@ForkJoinPool-1-worker-1: sleeping for 1 second...
16:47:57.053 [bob] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#38,bob]/runnable@ForkJoinPool-1-worker-1: finished sleeping.
16:47:57.053 [alice] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#36,alice]/runnable@ForkJoinPool-1-worker-2: finished sleeping.
16:47:57.053 [bob] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#38,bob]/runnable@ForkJoinPool-1-worker-1: sleeping for 1 second...
16:47:57.053 [alice] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#36,alice]/runnable@ForkJoinPool-1-worker-2: sleeping for 1 second...
16:47:58.058 [bob] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#38,bob]/runnable@ForkJoinPool-1-worker-2: finished sleeping.
16:47:58.058 [alice] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#36,alice]/runnable@ForkJoinPool-1-worker-7: finished sleeping.
16:47:58.059 [alice] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#36,alice]/runnable@ForkJoinPool-1-worker-7: sleeping for 1 second...
16:47:58.059 [bob] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#38,bob]/runnable@ForkJoinPool-1-worker-2: sleeping for 1 second...
16:47:59.064 [alice] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#36,alice]/runnable@ForkJoinPool-1-worker-2: finished sleeping.
16:47:59.064 [alice] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#36,alice]/runnable@ForkJoinPool-1-worker-2: sleeping for 1 second...
16:47:59.064 [bob] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#38,bob]/runnable@ForkJoinPool-1-worker-6: finished sleeping.
16:47:59.064 [bob] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#38,bob]/runnable@ForkJoinPool-1-worker-7: sleeping for 1 second...
16:48:00.069 [alice] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#36,alice]/runnable@ForkJoinPool-1-worker-7: finished sleeping.
16:48:00.069 [alice] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#36,alice]/runnable@ForkJoinPool-1-worker-7: sleeping for 1 second...
16:48:00.069 [bob] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#38,bob]/runnable@ForkJoinPool-1-worker-2: finished sleeping.
16:48:00.069 [bob] INFO com.callibrity.vthreads.examples.CooperativeScheduling -- VirtualThread[#38,bob]/runnable@ForkJoinPool-1-worker-2: sleeping for 1 second...
```


### Throughput/Load Testing

```shell
sudo sysctl -w net.inet.tcp.msl=1000
sudo sysctl -w net.inet.ip.portrange.first=32768
```
