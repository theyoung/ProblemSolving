package leetcode.implementation;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
analyzeIpAddresses
https://app.codesignal.com/company-challenges/verkada
 */
//TODO BufferedReader, FileReader, try with resource, Pattern, Matcher 등을 활용하는 테스트
public class AnalyzeIpAddresses {

    @Test
    void test() {
        File directory = new File("./src/main/java/leetcode/implementation/");

        List<File> list = new ArrayList<>();

        LinkedList<File> queue = new LinkedList<>();
        queue.add(directory);

        while (!queue.isEmpty()) {
            File file = queue.poll();
            if(file.isFile()) list.add(file);
            else {
                String[] subs = file.list();
                for(String path : subs){
                    queue.add(new File(file.getAbsoluteFile()+"/"+path));
                }
            }
        }

        Set<String> set = new HashSet<>();

        for(File file : list){
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line = "";
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})");
                    Matcher matcher = pattern.matcher(line);


                    while(matcher.find()){
                        String str = matcher.group(0);
                        String[] ips = str.split("\\.");
                        boolean check = true;
                        for(int i = 0; i < 4; i++){
                            int val = Integer.parseInt(ips[i]);
                            if(val < 0 || 255 < val) check = false;
                        }
                        if(check){
                            set.add(matcher.group(0));
                        }
                    }
                }
            } catch (Exception e){
                continue;
            }
        }

        for(String str : set){
            System.out.println(str);
        }

    }

}
