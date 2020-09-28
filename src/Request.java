import java.util.Random;

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
 * 일의 리퀘스트를 나타내는 클래스
 *
 */
public class Request {
    private final String name;
    private final int number;
    private static final Random random = new Random();

    // 생성자
    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /**
     * 현재 리퀘스트의 "처리"를 기술하고 있는 메소드
     * 현재 일하고 있는 쓰레드를 표시해주는 기능밖에 없긴 하다
     */
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " excutes " + this);

        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String toString() {
        return "[Request from " + name + " No. " + number + "]";
    }
}
