package with_JianzhiOffer;

import java.util.InputMismatchException;

/**
 * Created by qlf_workpc on 2017/3/2 0002.
 */
public class LearnSingleObject {
    public static class SingleObject{
        private static SingleObject INSTANCE = new SingleObject();

        private SingleObject(){

        }
        public  static SingleObject getInstance(){
            return INSTANCE;
        }
    }

    public static class SingleObject1{
        private static SingleObject1 INSTANCE1 = null;

        private SingleObject1(){

        }
        public static SingleObject1 getInstance(){
            if (INSTANCE1 == null){
                INSTANCE1 = new SingleObject1();
            }
            return INSTANCE1;
        }
    }

    public static class SingleObject2{
        private static SingleObject2 INSTANCE2 = null;

        private SingleObject2(){

        }

        public static synchronized SingleObject2 getInstance(){
            if (INSTANCE2 == null){
                INSTANCE2 = new SingleObject2();
            }
            return INSTANCE2;
        }
    }

    public static class SingleObject3{
        private static volatile SingleObject3 INSTANCE3;

        private SingleObject3(){

        }

        public static SingleObject3 getInstance(){
            if (INSTANCE3 == null){
                synchronized (SingleObject3.class){
                    if (INSTANCE3 == null){
                        INSTANCE3 = new SingleObject3();
                    }
                }
            }
            return INSTANCE3;
        }
    }

    public static class SingleObject4{
        private static class SingleObjectorHolder{
            private static final SingleObject4 INSTANCE = new SingleObject4();
        }

        private SingleObject4(){

        }
        private static final SingleObject4 getInstance(){
            return SingleObjectorHolder.INSTANCE;
        }
    }

    public enum SingleObject5{
        INSTANCE;

        public void whateverMethod() {

        }
    }
}
