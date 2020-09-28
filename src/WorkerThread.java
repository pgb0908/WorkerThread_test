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
 * 워커 쓰레드는 일을 실행
 * Channel의 인스턴스에서 takeReuest 메소드를 사용해서 request의 인스턴스를 하나 받음
 *   --> 그 인스턴스의 excute 메소드를 호출
 *
 * 워커 쓰레드는 한번 기동하면 영원히 일을 실행해 나감
 */
public class WorkerThread extends Thread{
    private final Channel channel;
    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    public void run() {
        while(true) {
            Request request = channel.takeRequest();
            request.execute();
        }
    }

}
