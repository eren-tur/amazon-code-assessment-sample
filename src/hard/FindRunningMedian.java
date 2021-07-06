import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.function.*;
        import java.util.regex.*;
        import java.util.stream.*;
        import static java.util.stream.Collectors.joining;
        import static java.util.stream.Collectors.toList;

class FindRunningMedianResult {

    /*
     * Complete the 'runningMedian' function below.
     *
     * The function is expected to return a DOUBLE_ARRAY.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static int partition(List<Integer> arr, int value, int begin, int end) {

        int arrSize = arr.size();
        if(arrSize == 0) return 0;

        int diff = end-begin;
        if(diff==0){
            if(arr.get(begin) > value) return begin;
            else return begin+1;
        }
        else if(diff==1){
            int findIndex = begin;
            for (int j= begin; j<=end; j++){
                if(arr.get(j)<=value){
                    findIndex = j+1;
                }
            }

            return findIndex;
        }

        int middle = begin+diff/2;

        if(arr.get(middle) > value){
            return partition(arr, value, begin, middle-1);
        }else{
            return partition(arr, value, middle+1, end);
        }

    }

    public static List<Double> runningMedian(List<Integer> a) {
        List<Double> results = new ArrayList<Double>();

        List<Integer> arr = new ArrayList<Integer>();
        for (int i=0; i<a.size(); i++){
            int addValue = a.get(i);

            arr.add(partition(arr, addValue, 0, arr.size()-1), addValue);

            double medianVal;
            int middle = arr.size()/2;
            if(arr.size()%2==1){
                medianVal = arr.get(middle).doubleValue();
            }else{
                int sumOfVal = arr.get(middle)+arr.get(middle-1);
                medianVal = (double)sumOfVal/2;
            }

            results.add(medianVal);
        }

        return results;
    }

}

public class FindRunningMedian {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Double> result = FindRunningMedianResult.runningMedian(a);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
