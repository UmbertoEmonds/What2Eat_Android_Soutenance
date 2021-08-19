package fr.projet2.what2eat.util.injections;

public class Injection {

    public static ViewModelFactory VM_FACTORY;

    public static ViewModelFactory provideViewModelFactory() {
        if(VM_FACTORY == null){
            VM_FACTORY = new ViewModelFactory();
        }
        return VM_FACTORY;
    }
}