package question1;

import java.util.Random;
import java.util.ArrayList;

public class randomSentences {
    public static void main(String[] args) {
        ArrayList<String> noun = new ArrayList<String>();
        noun.add("boy");
        noun.add("girl");
        noun.add("dog");
        noun.add("town");
        noun.add("car");
        ArrayList<String> verb = new ArrayList<String>();
        verb.add("drove");
        verb.add("jumped");
        verb.add("ran");
        verb.add("walked");
        verb.add("skipped");
        ArrayList<String> preposition = new ArrayList<String>();
        preposition.add("to");
        preposition.add("from");
        preposition.add("over");
        preposition.add("under");
        preposition.add("on");
        ArrayList<String> article = new ArrayList<String>();
        article.add("the");
        article.add("a");
        article.add("one");
        article.add("some");
        article.add("any");

        Random r = new Random();
        int randomNounItem = r.nextInt(noun.size());
        String randomNounElement = noun.get(randomNounItem);

        int randomVerbItem = r.nextInt(verb.size());
        String randomVerbElement = verb.get(randomVerbItem);

        int randomPItem = r.nextInt(preposition.size());
        String randomPElement = preposition.get(randomPItem);

        int randomArticleItem = r.nextInt(article.size());
        String randomArticleElement = article.get(randomArticleItem);

        String space = " ";
        String firstArticle = randomArticleElement.substring(0,1).toUpperCase() + randomArticleElement.substring(1).toLowerCase();
        String period = ".";


        StringBuilder sb = new StringBuilder();
        sb.append(firstArticle);
        sb.append(space);
        sb.append(randomNounElement);
        sb.append(space);
        sb.append(randomVerbElement);
        sb.append(space);
        sb.append(randomPElement);
        sb.append(space);
        sb.append(randomArticleElement);
        sb.append(space);
        sb.append(randomNounElement);
        sb.append(period);
        String result = sb.toString();
        System.out.println(result);

        //Example Using concat() method incase that was what was meant to be used
        //String result = str1.concat(str2).concat(str3).concat(str4);
    }
}
