package yoann.m2i.repertoire_telephone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText txtSearch;
    EditText txtId;
    EditText txtName;
    EditText txtTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbInit dbInit = new DbInit(this, "annuaire", 1);
        db = dbInit.getWritableDatabase();

        txtSearch = (EditText) findViewById(R.id.txtSearch);
        txtId = (EditText) findViewById(R.id.txtId);
        txtName = (EditText) findViewById(R.id.txtName);
        txtTel = (EditText) findViewById(R.id.txtTel);
    }

    public void search(View v){
        String[] cols = {"id", "name", "tel"};
        String critere = "UPPER(name) = '"
                + txtSearch.getText().toString().toUpperCase() + "'";
        Cursor curs = db.query("contacts", cols, critere, null,"","","");

        if(curs.getCount() == 0) {
            showToast("inexistant");
        }
        else {
            curs.moveToFirst();
            txtId.setText(curs.getString(0));
            txtName.setText(curs.getString(1));
            txtTel.setText(curs.getString(2));
        }
    }

    public void clear(View v) {
        txtSearch.setText("");
        txtId.setText("");
        txtName.setText("");
        txtTel.setText("");
    }

    public void save (View v) {
        ContentValues values = new ContentValues();
        values.put("name", txtName.getText().toString());
        values.put("tel", txtTel.getText().toString());

        if (txtId.getText().toString().equals("")){
            insert(values);
        }
        else {
            update(values, txtId.getText().toString());
        }
    }

    public void update(ContentValues values, String id){
        String crit = "id = '" + id + "'";
        db.update("contacts", values, crit, null);
        showToast("Le contact a été mis à jour");
    }

    public void insert(ContentValues values) {
        db.insert("contacts",null, values);
        showToast("Un contact a été ajouté");
    }

    public void delete(View v) {
        String crit = "id = '" + txtId.getText().toString() + "'";
        db.delete("contacts", crit, null);
        showToast("Le contact a été effacé");
    }

    public void showToast (String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


}
