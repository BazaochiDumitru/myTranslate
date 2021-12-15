package com.example.mytranslate.repositories;

import com.example.mytranslate.models.Definition;
import com.example.mytranslate.models.Word;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class WordTranslatorRepository {
    private Gson gson = new Gson();

    public String translateWord(String word, String language) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            return wordModel.toString();
        } catch (Exception e) {
            return "word not found";
        }
    }

    public boolean addWord(Word word, String language) {
        String fileName = "src/main/resources/translations/" + language + "/" + word.word + ".json";
        try {
            Writer writer = new FileWriter(fileName);
            gson.toJson(word, writer);
            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteWord(String word, String language) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            File file = new File(fileName);
            file.delete();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addDefinitionForWord(String word, String language, Definition definition) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            wordModel.definitions.add(definition);//todo
            try {
                Writer writer = new FileWriter(fileName);
                gson.toJson(wordModel, writer);
                writer.close();
            } catch (Exception e) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // delete
    public boolean deleteDefinition(String word, String language, String dictionary) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            wordModel.definitions.remove(dictionary);
            try {
                Writer writer = new FileWriter(fileName);
                gson.toJson(wordModel, writer);
                writer.close();
            } catch (Exception e) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // translate sentence
    public String translateSentence(String sentence, String fromLanguage, String toLanguage) {
        sentence = sentence.toLowerCase();
        String[] words = sentence.split("(?<=[a-z])\\\\.\\\\s+");
        for (String el : words) {
            System.out.println("word - " + el);
            String fileName_temp = "src/main/resources/translations/" + fromLanguage + "/" + el + ".json";
            try {
                Reader reader = Files.newBufferedReader(Paths.get(fileName_temp));
                Word wordModel = gson.fromJson(reader, Word.class);
                reader.close();
                return wordModel.toString();
            } catch (Exception e) {
                return "word not found";
            }
        }
        return "Something was happen";
    }

    // get Definitions & Synonyms
    public ArrayList<Definition> getDefinitionForWord(String word, String language) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        ArrayList<Definition> definitionArrayList = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            definitionArrayList = wordModel.getDefinitions();
            try {
                Writer writer = new FileWriter(fileName);
                gson.toJson(wordModel, writer);
                writer.close();
            } catch (Exception e) {
                return definitionArrayList;
            }
            return definitionArrayList;
        } catch (Exception e) {
            return definitionArrayList;
        }

    }
}
