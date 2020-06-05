package com.np.pramitmarattha.database;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.np.pramitmarattha.BR;

@Entity(tableName = "users", indices = {@Index(value = {"email"}, unique = true)})
public class EntireUserDatabase extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "full_name")
    private String fullName;
    @ColumnInfo(name = "user_name")
    private String userName;
    private String password;
    private String email;

    public EntireUserDatabase(long id, String fullName, String userName, String password, String email) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    @Ignore
    public EntireUserDatabase() {
    }
    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        notifyPropertyChanged(BR.fullName);
    }
    @Bindable
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }
    @Bindable
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
    @Bindable
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }
}