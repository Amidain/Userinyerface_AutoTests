package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.passay.*;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LoginDataGenerator {

    public static String generateEmailPrefix(){
        final int RND_LENGTH = 8;
        return RandomStringUtils.random(RND_LENGTH, true, true);
    }

    public static String generateMailBox(){
        final int MIN_LENGTH = 2;
        final int MAX_LENGTH = 5;
        return RandomStringUtils.randomAlphabetic(MIN_LENGTH,MAX_LENGTH);
    }

    public static String generateCustomPassword (String email){
        PasswordGenerator generator = new PasswordGenerator();

        CharacterData cyrilicLetter = CyrillicCharacterData.LowerCase;
        CharacterRule cyrilicRule = new CharacterRule(cyrilicLetter);
        cyrilicRule.setNumberOfCharacters(1);

        CharacterData alphabeticalChars = EnglishCharacterData.Alphabetical;
        CharacterRule alphabeticalRule = new CharacterRule(alphabeticalChars);
        alphabeticalRule.setNumberOfCharacters(7);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(1);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(1);

        int rndInt = new Random().nextInt(email.length());
        String emailChar = String.valueOf(email.charAt(rndInt));

        String customPass = generator.generatePassword(10, cyrilicRule, alphabeticalRule, upperCaseRule, digitRule).concat(emailChar);

        List<Character> chars = customPass.chars()
                .mapToObj(letter -> (char) letter)
                .collect(Collectors.toList());
        Collections.shuffle(chars);

        return chars.stream().collect(StringBuilder::new,StringBuilder::append, StringBuilder::append).toString();
    }
}

