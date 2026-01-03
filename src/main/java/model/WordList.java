package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class WordList {
    private int id;
    private String new_word;
    private String meaningOfWord;
    private String  sinhala_Meaning;
    private int created_date;

}
