import java.io.*;

public class Main {
    static int size = 0;
    static int[] heap = new int[100_001];

    public static void push(int value){
        heap[++size] = value;

        int index = size;

        while(index != 1){
            int parent = index/2;

            if(heap[index] > heap[parent]){
                int temp = heap[index];
                heap[index] = heap[parent];
                heap[parent] = temp;

                index = parent;
            }
            else{
                break;
            }
        }
    }

    public static int pop(){
        if(size == 0){
            return 0;
        }

        int result = heap[1];
        heap[1] = heap[size--];

        int index = 1;
        while(true){
            int child = index*2;

            if(child+1<=size && heap[child] < heap[child+1]){
                child++;
            }

            if(child>size) break;

            if(heap[index] < heap[child]){
                int temp = heap[index];
                heap[index] = heap[child];
                heap[child] = temp;

                index = child;
            }
            else{
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++){
            int value = Integer.parseInt(br.readLine());

            if(value == 0){
                sb.append(pop()).append('\n');
            }
            else{
                push(value);
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}