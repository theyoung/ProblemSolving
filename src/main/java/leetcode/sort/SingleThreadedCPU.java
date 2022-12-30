package leetcode.sort;
import java.util.*;
/*
1834. Single-Threaded CPU
https://leetcode.com/problems/single-threaded-cpu/description/
 */
//TODO sorting을 기본으로 하면서, time이전의 task가 모두 동일한 기준이라는 착안을 하는게 중요했다.
public class SingleThreadedCPU {
    //T.C는 O(nlogn)이다 sorting에 가장 많은 시간을 쓰고, tasklist는 한번씩만 접근함으로 nlogn이다
    //S.C는 O(n) tasks의 length를 담는 큐와 Task 리스트가 필요함으로 n이다
    class Task {
        int index;
        int startTime;
        int endTime;

        public Task(int index, int startTime, int endTime){
            this.index = index;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public int[] getOrder(int[][] tasks) {
        //tasks에는 시작 시간이 있다
        // 시작시간 <= time 이라면 pool 안에서
        //  - 가장 짧은 프로세스 타임
        //  - 가장 낮은 index 순
        // 으로 프로세스를 탈 수 있다
        PriorityQueue<Task> queue = new PriorityQueue<>((a,b)->{
            if(a.endTime != b.endTime) return a.endTime - b.endTime;
            return a.index - b.index;
        });

        Task[] taskList = new Task[tasks.length];
        for(int i = 0; i < taskList.length; i++) taskList[i] = new Task(i, tasks[i][0],tasks[i][1]);

        // pool이 empty라면 다음 task를 실행 시킨다
        // time = next task 시작 시간
        // time = time + process 시간 이 된다
        Arrays.sort(taskList, (a,b)->a.startTime-b.startTime);

        // pool이 empty가 아니고 time < next task라면
        // pool에서 next task를 처리한다
        // pool이 empty가 되거나 next task < time이 될때까지 순환된다
        List<Integer> results = new ArrayList<>();

        int time = 1;
        int index = 0;

        while(index < taskList.length && time <= taskList[taskList.length-1].startTime){
            while(index < taskList.length && taskList[index].startTime <= time){
                queue.add(taskList[index]);
                index++;
            }

            if(queue.isEmpty()){
                time = taskList[index].startTime;
            } else {
                Task task = queue.poll();
                results.add(task.index);
                time = time + task.endTime;
            }
        }

        while(index < taskList.length){
            queue.add(taskList[index]);
            index++;
        }

        while(!queue.isEmpty()){
            Task task = queue.poll();
            results.add(task.index);
        }

        return results.stream().mapToInt((i)->i).toArray();
    }
}
