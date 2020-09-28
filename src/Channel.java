/*
 * Copyright 2020 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Tmax
 *
 * 일의 리퀘스트를 주고 받는 것과 워커 쓰레드
 */
public class Channel {
    private static final int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private int tail;   // 다음에 putRequest하는 장소
    private int head;   // 다음에 takeReuest하는 장소
    private int count;  // Ruequest 수

    private final WorkerThread[] threadPool;

    /**
     * @param threads
     */
    public Channel(int threads) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;

        threadPool = new WorkerThread[threads];
        for(int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread("Worker-"+i, this);
        }
    }

    /**
     *
     */
    public void startWorkers() {
        for(int i=0; i<threadPool.length; i++) {
            threadPool[i].start();
        }
    }

    /**
     * @param request
     *
     */
    public synchronized void putRequest(Request request) {
        while(count >= requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        notifyAll();
    }


    /**
     * @return
     */
    public synchronized Request takeRequest() {
        while(count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Request request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();

        return request;
    }



}
