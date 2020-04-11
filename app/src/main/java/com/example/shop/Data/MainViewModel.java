package com.example.shop.Data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static PartsDataBase dataBase;
    private LiveData<List<Part>> parts;
    private LiveData<List<PartsToBasket>> partsToBasket;

    public MainViewModel(@NonNull Application application) {
        super(application);
        dataBase = PartsDataBase.getInstance(getApplication());
        parts = dataBase.partsDao().getAllPart();
        partsToBasket = dataBase.partsDao().getAllPartToBasket();
    }



    public LiveData<List<PartsToBasket>> getPartsToBasket() {
        return partsToBasket;
    }

    public LiveData<List<Part>> getParts() {
        return parts;
    }

    public void insertPartToBasket (PartsToBasket partsToBasket) {
        new InsertTaskPartToBasket().execute(partsToBasket);
    }

    public static class  InsertTaskPartToBasket extends AsyncTask <PartsToBasket, Void, Void> {
        @Override
        protected Void doInBackground(PartsToBasket... partsToBaskets) {
            if (partsToBaskets != null || partsToBaskets.length > 0) {
                dataBase.partsDao().insertPartToBasket(partsToBaskets[0]);
            }
            return null;
        }
    }

    public void deletePartToBasket (PartsToBasket partsToBasket) {
        new DeleteTaskPartToBasket().execute(partsToBasket);
    }

    public static class  DeleteTaskPartToBasket extends AsyncTask <PartsToBasket, Void, Void> {
        @Override
        protected Void doInBackground(PartsToBasket... partsToBaskets) {
            if (partsToBaskets != null || partsToBaskets.length > 0) {
                dataBase.partsDao().deletePartToBasket(partsToBaskets[0]);
            }
            return null;
        }
    }

    public void insertPart(List<Part> partFromJSON) {
            new InsertTask().execute((ArrayList<Part>) partFromJSON);
           }


    public static class InsertTask extends AsyncTask<ArrayList<Part>, Void, Void> {
        @Override
        protected Void doInBackground(ArrayList<Part>... parts) {
            if (parts != null || parts.length > 0) {
                dataBase.partsDao().insertParts((List<Part>) parts[0]);
            }
            return null;
        }
    }

    public void deleteAllParts () {
        new DeleteAllParts().execute();
    }

    public static class DeleteAllParts extends AsyncTask <Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            dataBase.partsDao().deleteAllParts();
            return null;
        }
    }
    public void deleteAllPartsToBasket () {
        new DeleteAllPartsToBasket().execute();
    }

    public static class DeleteAllPartsToBasket extends AsyncTask <Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            dataBase.partsDao().deleteAllPartToBasket();
            return null;
        }
    }
}
