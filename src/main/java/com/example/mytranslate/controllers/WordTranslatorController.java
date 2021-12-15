package com.example.mytranslate.controllers;

import com.example.mytranslate.models.Definition;
import com.example.mytranslate.models.Word;
import com.example.mytranslate.repositories.WordTranslatorRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordTranslatorController {

    private WordTranslatorRepository wordTranslatorRepository = new WordTranslatorRepository();

    @GetMapping(path = "translate/word/{language}/{word}")
    public String translateWord(@PathVariable String word, @PathVariable String language){
        return wordTranslatorRepository.translateWord(word, language);
    }

    @PostMapping(path = "translate/word/{language}")
    public boolean addWord(@RequestBody Word word, @PathVariable String language){
        return wordTranslatorRepository.addWord(word, language);
    }

    @DeleteMapping(path = "translate/word/{language}/{word}")
    public boolean deleteWord(@PathVariable String word, @PathVariable String language){
        return wordTranslatorRepository.deleteWord(word, language);
    }

    @PostMapping(path = "translate/word/{language}/{word}")
    public boolean addDefinitionForWord(@PathVariable String word, @PathVariable String language, @RequestBody Definition definition){
        return wordTranslatorRepository.addDefinitionForWord(word, language, definition);
    }

    // Metodă pentru ștergerea unei definiții a unui cuvânt dat ca parametru o booleanremoveDefinition(Stringword, Stringlanguage, Stringdictionary)
    // Întoarce true dacă s-a șters definiția sau false dacă nu există o definiție din dicționarul primit ca parametru
    @DeleteMapping(path = "translate/word/{language}/{word}/{dictionary}")
    public boolean deleteDefinition(@PathVariable String word, @PathVariable String language, @PathVariable String dictionary, @RequestBody Definition definition){
        return wordTranslatorRepository.deleteDefinition(word, language, definition);
    }


    // Metodă pentru traducerea unei propoziții o String translateSentence(String sentence, String fromLanguage, String toLanguage)
    // Întoarce traducerea propoziției sentence din limba fromLanguage în limba toLanguage



    // Metodă pentru întoarcerea definițiilor și sinonimelor unui cuvânt o ArrayList getDefinitionsForWord(String word, String language)
    // Definițiile sunt sortate crescător după anul de apariție al dicționarului
    /*@GetMapping(path = "translate/word/{language}/{word}")
    public String getDefinitionForWord(@PathVariable String word, @PathVariable String language){
        return wordTranslatorRepository.getDefinitionForWord(word, language);
    }*/
}
