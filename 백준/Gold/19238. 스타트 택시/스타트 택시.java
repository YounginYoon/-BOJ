//package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, gas, taxiR, taxiC;
    static int[][] map;
    static int[][] startCustomer;
    static ArrayList<Customer> arrivePos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        arrivePos = new ArrayList<>();
        startCustomer = new int[n][n];
        //arriveCustomer = new int[n][n];

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxiR = Integer.parseInt(st.nextToken()) - 1;
        taxiC = Integer.parseInt(st.nextToken()) - 1;

        int idx = 2;
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken()) - 1;
            int sc = Integer.parseInt(st.nextToken()) - 1;
            int er = Integer.parseInt(st.nextToken()) - 1;
            int ec = Integer.parseInt(st.nextToken()) - 1;

            //map[sr][sc] = idx;
            //map[er][ec] = -idx;
            startCustomer[sr][sc] = idx;
            //arriveCustomer[er][ec] = idx;
            arrivePos.add(new Customer(er, ec, 0));
            idx++;
        }

        while (true) {
            ArrayList<Customer> canRideCustomers = rideCustomer(taxiR, taxiC);

            if (canRideCustomers.size() == 0 && gas >= 0) { // 태울 승객이 더이상 없을 때
                if (m > 0) {
                    // System.out.println("태워다 줄 수 있는 승객이 없음");
                    System.out.println(-1);
                }
                else if (m == 0) {
                    System.out.println(gas);
                }
                return;
            }

//            System.out.println("canRideCustomer!");
//            for (Customer c: canRideCustomers) {
//                System.out.printf("%d: %d %d %d\n", (map[c.r][c.c] - 1) , c.d, c.r, c.c);
//            }

            taxiR = canRideCustomers.get(0).r;
            taxiC = canRideCustomers.get(0).c;
            int usedGas = canRideCustomers.get(0).d;
            //System.out.println("start " + taxiR + " " + taxiC + " " + usedGas + " " + (map[taxiR][taxiC] - 1));

            gas -= usedGas; // 승객을 태우러 감
            if (gas <= 0) { // 승객을 태우러 가는 도중, 연료를 다 쓴다면 -> 모든 승객을 다 태울 수 없으므로 -1
//                System.out.println("태우러 가는 도중 연료 다씀");
                System.out.println(-1);
                return;
            }

            int startR = taxiR;
            int startC = taxiC;
            int canArrive = finishTaxi(taxiR, taxiC);

            if (canArrive == -1) { // 태운 승객을 도착지로 보낼 수 없으면 실패
//                System.out.println("승객을 도착지로 데려다 줄 수 없음");
                System.out.println(-1);
                return;
            }

            gas -= canArrive; // 승객을 데려다 줌
            if (gas < 0) {
                // 태워다 주는 도중에 연료를 다 쓴다면 실패
//                System.out.println("데려다 주는 도중 연료 다씀");
                System.out.println(-1);
                return;
            }
            // 승객 이동 성공 -> 승객을 태워다 줄 때 쓴 연료량의 2배를 채움
            gas += (canArrive * 2);
            // 승객 이동에 성공했으므로 지도를 빈칸 처리
            startCustomer[startR][startC] = 0;
            m--;
            //System.out.println("Arrive! " + canArrive + " " + gas);

        }

    }


    public static void printMap(int[][] tmp) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.printf("%d ", tmp[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isOutOfRange(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= n) return true;
        return false;
    }

    public static ArrayList<Customer> rideCustomer(int sr, int sc) {
        Queue<Taxi> q = new LinkedList<>();
        ArrayList<Customer> canRideCustomers = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        int[][] dist = new int[n][n];
        int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        q.offer(new Taxi(sr, sc));
        visited[sr][sc] = true;

        // 시작 위치에 승객이 있는지 확인
        if (startCustomer[sr][sc] > 1) {
            canRideCustomers.add(new Customer(sr, sc, 0));
        }

        while (!q.isEmpty()) {
            Taxi cur = q.poll();

            for(int d=0;d<4;d++) {
                int nr = cur.r + dir[d][0]; int nc = cur.c + dir[d][1];
                if (!isOutOfRange(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
                    q.offer(new Taxi(nr, nc));
                    dist[nr][nc] = dist[cur.r][cur.c] + 1;
                    visited[nr][nc] = true;

                    if (startCustomer[nr][nc] > 1 && gas >= dist[nr][nc]) { // 승객이 있으면 리스트에 추가
                        canRideCustomers.add(new Customer(nr, nc, dist[nr][nc]));
                    }
                }
            }
        }

        Collections.sort(canRideCustomers);
        return canRideCustomers;

    }

    public static int finishTaxi(int sr, int sc) {
        boolean[][] visited = new boolean[n][n];
        Queue<Move> q = new LinkedList<>();
        int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        q.offer(new Move(sr, sc, 0));
        visited[sr][sc] = true;
        //int target = map[sr][sc] * -1;
        int cusIdx = startCustomer[sr][sc] - 2;
        Customer arrive = arrivePos.get(cusIdx);
        int er = arrive.r;
        int ec = arrive.c;

        while (!q.isEmpty()) {
            Move cur = q.poll();

            if (cur.r == er && cur.c == ec) {
                // 택시 위치를 도착 위치로 갱신
                taxiR = cur.r;
                taxiC = cur.c;
                return cur.d;
            }

            for(int d=0;d<4;d++) {
                int nr = cur.r + dir[d][0];
                int nc = cur.c + dir[d][1];
                if (!isOutOfRange(nr,nc) && !visited[nr][nc] && (map[nr][nc] != 1)) {
                    q.offer(new Move(nr, nc, cur.d + 1));
                    visited[nr][nc] = true;
                }
            }
        }

        return -1;
    }
}

class Taxi {
    int r, c;
    Taxi(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Move {
    int r, c, d;
    Move(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

class Customer implements Comparable<Customer> {
    int r, c, d;
    Customer(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }

    @Override
    public int compareTo(Customer customer) {
        if (this.d == customer.d) {
            if (this.r == customer.r) return this.c - customer.c;
            return this.r - customer.r;
        }
        return this.d - customer.d;
    }
}

