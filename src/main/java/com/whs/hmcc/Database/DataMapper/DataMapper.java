package com.whs.hmcc.Database.DataMapper;

import android.content.Context;

import com.whs.hmcc.Database.Model.Model;

abstract public class DataMapper {
    protected Model theModel;
    protected Context myContext;

    public DataMapper(Context context) {
        myContext = context;
        setupModel();
        openConnection();
    }

    protected abstract void setupModel();

    private void openConnection() {
        theModel.open();
    }

    private void closeConnection() {
        theModel.close();
    }

    @Override
    protected void finalize() throws Throwable {
        closeConnection();
    }
}
