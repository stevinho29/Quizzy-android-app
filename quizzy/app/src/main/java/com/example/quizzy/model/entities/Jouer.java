package com.example.quizzy.model.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"id_user", "id_party"})
public class Jouer {   // la table jouer se décline de la relation many to many (MCD -> MLD)

    @NonNull
    public Integer id_user;
    @NonNull
    public Integer id_party;

    public Jouer(Integer id_user,Integer id_party){  // résulte de l'implémentation du MCD EN MLD: équivalent d'une table participer
        this.id_party= id_party;
        this.id_user= id_user;
    }

    @NonNull
    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(@NonNull Integer id_user) {
        this.id_user = id_user;
    }

    @NonNull
    public Integer getId_party() {
        return id_party;
    }

    public void setId_party(@NonNull Integer id_party) {
        this.id_party = id_party;
    }
}
