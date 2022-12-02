package leetcode.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
productManagement
https://app.codesignal.com/company-challenges/verkada
 */
//25분
//TODO 10분에 끝냈어야 했다. 문제를 주의 깊게 읽었어야 했다.
public class ProductManager {
    class Product {
        int id;
        public String title;

        Product(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public String toString(){
            return String.format("{\"id\":%d,\"title\":%s}",id,title);
        }

        public boolean equals(Product target){
            return this.id == target.id;
        }
    }
    Map<Integer,Product> idMap;
    Map<String, Product> titleMap;
    ProductManager() {
        idMap = new HashMap<>();
        titleMap = new HashMap<>();
    }

    /*
    creates a record for a new product. Returns false if a product with the specified id already exists, and true otherwise;
    */
    boolean createProduct(int id, String title) {
        // TODO: return false if the product id already exists
        if(idMap.containsKey(id)) return false;

        Product product = new Product(id,title);
        idMap.put(id, product);
        titleMap.put(title,product);
        return true;
    }
    /*
    updates the product with the provided info. Returns false if the product with such id does not exist, and true otherwise.
    */
    boolean updateProduct(int id, String title) {
        // TODO: return false if the product id does not exist
        if(!idMap.containsKey(id)) return false;
        Product product = idMap.get(id);
        titleMap.remove(product.title);

        product.title = title;

        titleMap.put(product.title, product);

        return true;
    }

    /*
    deletes the provided product. Returns false if the product with this id does not exist, and true otherwise.
    */
    boolean deleteProduct(int id) {
        // TODO: return false if the product does not exist
        if(!idMap.containsKey(id)) return false;
        Product product = idMap.get(id);
        idMap.remove(id);
        titleMap.remove(product.title);
        return true;
    }
    /*
    finds a product by id. Returns the product (in the form of a JSON) if the product with this id exists, and null otherwise.
    */
    Product findProductById(int id) {
        return idMap.get(id);
    }
    /*
    find a product by title. Returns the product (in the form of a JSON) if the product with this title exists, and null otherwise
    */
    Product findProductByTitle(String title) {
        return titleMap.get(title);
    }

    ProductManager productManager = new ProductManager();
    List<String> solution(String[][] operations) {
        // Calls corresponding methods of productManager based on the input
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < operations.length; i++) {
            if (operations[i][0].equals("createProduct")) {
                int id = Integer.parseInt(operations[i][1]);
                if (productManager.createProduct(id, operations[i][2])) {
                    answer.add("true");
                } else {
                    answer.add("false");
                }
            }
            if (operations[i][0].equals("updateProduct")) {
                int id = Integer.parseInt(operations[i][1]);
                if (productManager.updateProduct(id, operations[i][2])) {
                    answer.add("true");
                } else {
                    answer.add("false");
                }
            }
            if (operations[i][0].equals("deleteProduct")) {
                int id = Integer.parseInt(operations[i][1]);
                if (productManager.deleteProduct(id)) {
                    answer.add("true");
                } else {
                    answer.add("false");
                }
            }
            if (operations[i][0].equals("findProductById")) {
                int id = Integer.parseInt(operations[i][1]);
                Product result = productManager.findProductById(id);
                if (result == null) {
                    answer.add("null");
                } else {
                    answer.add("{\"id\":\"" + Integer.toString(result.id) + "\",\"title\":\"" + result.title + "\"}");
                }
            }
            if (operations[i][0].equals("findProductByTitle")) {
                String title = operations[i][1];
                Product result = productManager.findProductByTitle(title);
                if (result == null) {
                    answer.add("null");
                } else {
                    answer.add("{\"id\":\"" + Integer.toString(result.id) + "\",\"title\":\"" + result.title + "\"}");
                }
            }
        }
        return answer;
    }

}
