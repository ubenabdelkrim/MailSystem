package part4;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to register invocated methods
 */
public class InvocationsLog {
    private List<String> methodsList;

    public InvocationsLog(){
        methodsList=new ArrayList<>();
    }

    public List<String> getMethodsList() {
        return methodsList;
    }

    public void setMethodsList(List<String> methodsList) {
        this.methodsList = methodsList;
    }

    public void add(String method){
        methodsList.add(method);
    }

    public void remove(String method){
        methodsList.remove(method);
    }

}
