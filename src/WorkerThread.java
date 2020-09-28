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
 * ��Ŀ ������� ���� ����
 * Channel�� �ν��Ͻ����� takeReuest �޼ҵ带 ����ؼ� request�� �ν��Ͻ��� �ϳ� ����
 *   --> �� �ν��Ͻ��� excute �޼ҵ带 ȣ��
 *
 * ��Ŀ ������� �ѹ� �⵿�ϸ� ������ ���� ������ ����
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
