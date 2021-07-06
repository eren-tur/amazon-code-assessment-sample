package hard;

import java.io.*;
        import java.util.*;
        import java.util.Map.Entry;

public class MinimumAverageWaitingTime {
    private static long computeMinAvg(NavigableMap<Long, List<Customer>> ordersByTime) {
        PriorityQueue<Customer> waitingCustomers = new PriorityQueue<>();
        long currTime = 0;
        long totalWaitTime = 0;

        while (!ordersByTime.isEmpty() || !waitingCustomers.isEmpty()) {
            if (waitingCustomers.isEmpty()) {
                Entry<Long, List<Customer>> firstEntry = ordersByTime.pollFirstEntry();
                waitingCustomers.addAll(firstEntry.getValue());
                currTime = firstEntry.getKey();
            } else {
                Customer nextToCook = waitingCustomers.poll();
                currTime += nextToCook.cookTime;
                totalWaitTime += currTime - nextToCook.arrivalTime;
                NavigableMap<Long, List<Customer>> arrivals = ordersByTime.headMap(currTime, true);
                arrivals.values()
                        .stream()
                        .forEach(waitingCustomers::addAll);
                arrivals.clear();
            }
        }
        return totalWaitTime;
    }

    private static class Customer implements Comparable<Customer> {
        final long arrivalTime;
        final Long cookTime;

        Customer(long arrivalTime, long cookTime) {
            this.arrivalTime = arrivalTime;
            this.cookTime = cookTime;
        }

        @Override
        public int compareTo(Customer o) {
            return cookTime.compareTo(o.cookTime);
        }
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();

            NavigableMap<Long, List<Customer>> ordersByTime = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                Customer customer = new Customer(in.nextInt(), in.nextInt());
                ordersByTime.computeIfAbsent(customer.arrivalTime,
                        a -> new ArrayList<>())
                        .add(customer);
            }
            System.out.println(computeMinAvg(ordersByTime) / n);
        }
    }
}
