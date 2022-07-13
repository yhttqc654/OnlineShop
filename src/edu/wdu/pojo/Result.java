package edu.wdu.pojo;

public class Result {
    private String Flag;
    private Object Data;

    public Result() {
    }

    public Result(String flag) {
        Flag = flag;
    }

    public Result(String flag, Object data) {
        Flag = flag;
        Data = data;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "Flag='" + Flag + '\'' +
                ", Data=" + Data +
                '}';
    }
}
