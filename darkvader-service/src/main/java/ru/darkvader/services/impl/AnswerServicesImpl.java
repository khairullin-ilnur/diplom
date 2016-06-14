package ru.darkvader.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.darkvader.dao.AnswerDAO;
import ru.darkvader.exception.NotFoundException;
import ru.darkvader.model.*;
import ru.darkvader.services.AnswerServices;
import ru.darkvader.utils.ResolveLinearSystems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilnur on 12/03/16.
 * This implementation of the AnswerServices interface communicates with
 * the database by using a Spring Data JPA repository.
 *
 * @author Khairullin
 */

@Service
@Transactional
public class AnswerServicesImpl implements AnswerServices {

    @Autowired
    private AnswerDAO answerDAO;


    //------------------
    //------CRUD queries

    @Override
    public Answer create(Answer object) {
        return answerDAO.save(object);
    }

    @Override
    public Answer delete(int objectId) throws NotFoundException {
        Answer answer = answerDAO.findOne(objectId);
        if (answer == null) {
            throw new NotFoundException(objectId, Answer.class);
        }
        answerDAO.delete(answer);
        return answer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Answer> findAll() {
        return (List<Answer>) answerDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Answer findById(int objectId) throws NotFoundException {
        return answerDAO.findOne(objectId);
    }

    @Override
    public Answer update(Answer object) throws NotFoundException {
        Answer oldObject = answerDAO.findOne(object.getAnswerId());
        if (oldObject == null) {
            throw new NotFoundException(object.getAnswerId(), Answer.class);
        }
        return answerDAO.save(object);
    }

    //---------------------
    //------Special queries

    @Override
    public AnswerDTO createReportByLink(String csvFile1, String csvFile2, String movieId) {
        ArrayList<Person> persons = new ArrayList<>();
        Result result1 = new Result();
        Result result2 = new Result();
        ArrayList<PersonPoint> personPoints = new ArrayList<>();
        BufferedReader br1 = null;
        BufferedReader br2 = null;

        try {
            br1 = new BufferedReader(new FileReader("/Users/ilnur/IdeaProjects/DarkVader/" + csvFile1));
            br2 = new BufferedReader(new FileReader("/Users/ilnur/IdeaProjects/DarkVader/" + csvFile2));
            String line1 = br1.readLine();
            String line2 = br2.readLine();
            Person person;


            while (((line1 = br1.readLine()) != null) && ((line2 = br2.readLine()) != null)) {
                person = new Person();
                person.setEmotions1(parseFirstEmotions(line1));
                person.setEmotions2(parseSecondEmotions(line2));
                persons.add(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br1 != null) {
                try {
                    br1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br2 != null) {
                try {
                    br2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (Person person : persons) {
            int sizeOfMatrix = 3;
            double[][] matrix = new double[sizeOfMatrix][sizeOfMatrix + 1];
            // Compile first solution by y
            matrix[0][0] = 9 * (-1 * person.getEmotions1().get(3).getEmotionTotal() + person.getEmotions2().get(3).getEmotionTotal()) - 9 * (-1 * person.getEmotions1().get(4).getEmotionTotal() + person.getEmotions2().get(4).getEmotionTotal());
            matrix[0][1] = 81;
            matrix[0][2] = 9 * (-1 * person.getEmotions1().get(6).getEmotionTotal() + person.getEmotions2().get(6).getEmotionTotal()) - 9 * (-1 * person.getEmotions1().get(4).getEmotionTotal() + person.getEmotions2().get(4).getEmotionTotal());
            matrix[0][3] = -81 * (-1 * person.getEmotions1().get(6).getEmotionTotal() + person.getEmotions2().get(6).getEmotionTotal());
            // Compile second solution by z
            matrix[1][0] = 9 * (-1 * person.getEmotions1().get(6).getEmotionTotal() + person.getEmotions2().get(6).getEmotionTotal()) - 9 * (-1 * person.getEmotions1().get(5).getEmotionTotal() + person.getEmotions2().get(5).getEmotionTotal());
            matrix[1][1] = 9 * (-1 * person.getEmotions1().get(6).getEmotionTotal() + person.getEmotions2().get(6).getEmotionTotal()) - 9 * (-1 * person.getEmotions1().get(8).getEmotionTotal() + person.getEmotions2().get(8).getEmotionTotal());
            matrix[1][2] = 81;
            matrix[1][3] = 81 * (-1 * person.getEmotions1().get(8).getEmotionTotal() + person.getEmotions2().get(8).getEmotionTotal());
            // Compile third solution by x
            matrix[2][0] = 81;
            matrix[2][1] = 9 * (-1 * person.getEmotions1().get(1).getEmotionTotal() / 3.3 + person.getEmotions2().get(1).getEmotionTotal() / 3.3) - 9 * (-1 * person.getEmotions1().get(3).getEmotionTotal() + person.getEmotions2().get(3).getEmotionTotal());
            matrix[2][2] = 9 * (-1 * person.getEmotions1().get(1).getEmotionTotal() / 3.3 + person.getEmotions2().get(1).getEmotionTotal() / 3.3) - 9 * (-1 * person.getEmotions1().get(2).getEmotionTotal() + person.getEmotions2().get(2).getEmotionTotal());
            matrix[2][3] = 81 * (-1 * person.getEmotions1().get(1).getEmotionTotal() / 3.3 + person.getEmotions2().get(1).getEmotionTotal() / 3.3);

            ResolveLinearSystems rls = new ResolveLinearSystems(matrix);
            double[] resultPoint = rls.getResult();

            // Compile first solution by y
            matrix[0][0] = 9 * (person.getEmotions1().get(3).getEmotionTotal()) - 9 * (person.getEmotions1().get(4).getEmotionTotal());
            matrix[0][1] = 81;
            matrix[0][2] = 9 * (person.getEmotions1().get(6).getEmotionTotal()) - 9 * (person.getEmotions1().get(4).getEmotionTotal());
            matrix[0][3] = -81 * (person.getEmotions1().get(6).getEmotionTotal());
            // Compile second solution by z
            matrix[1][0] = 9 * (person.getEmotions1().get(6).getEmotionTotal()) - 9 * (person.getEmotions1().get(5).getEmotionTotal());
            matrix[1][1] = 9 * (person.getEmotions1().get(6).getEmotionTotal()) - 9 * (person.getEmotions1().get(8).getEmotionTotal());
            matrix[1][2] = 81;
            matrix[1][3] = 81 * (person.getEmotions1().get(8).getEmotionTotal());
            // Compile third solution by x
            matrix[2][0] = 81;
            matrix[2][1] = 9 * (person.getEmotions1().get(1).getEmotionTotal() / 3.3) - 9 * (person.getEmotions1().get(3).getEmotionTotal());
            matrix[2][2] = 9 * (person.getEmotions1().get(1).getEmotionTotal() / 3.3) - 9 * (person.getEmotions1().get(2).getEmotionTotal());
            matrix[2][3] = 81 * (person.getEmotions1().get(1).getEmotionTotal() / 3.3);

            rls = new ResolveLinearSystems(matrix);
            double[] resultOldPoint = rls.getResult();

            // Compile first solution by y
            matrix[0][0] = 9 * (person.getEmotions2().get(3).getEmotionTotal()) - 9 * (person.getEmotions2().get(4).getEmotionTotal());
            matrix[0][1] = 81;
            matrix[0][2] = 9 * (person.getEmotions2().get(6).getEmotionTotal()) - 9 * (person.getEmotions2().get(4).getEmotionTotal());
            matrix[0][3] = -81 * (person.getEmotions2().get(6).getEmotionTotal());
            // Compile second solution by z
            matrix[1][0] = 9 * (person.getEmotions2().get(6).getEmotionTotal()) - 9 * (person.getEmotions2().get(5).getEmotionTotal());
            matrix[1][1] = 9 * (person.getEmotions2().get(6).getEmotionTotal()) - 9 * (person.getEmotions2().get(8).getEmotionTotal());
            matrix[1][2] = 81;
            matrix[1][3] = 81 * (person.getEmotions2().get(8).getEmotionTotal());
            // Compile third solution by x
            matrix[2][0] = 81;
            matrix[2][1] = 9 * (person.getEmotions2().get(1).getEmotionTotal() / 3.3) - 9 * (person.getEmotions2().get(3).getEmotionTotal());
            matrix[2][2] = 9 * (person.getEmotions2().get(1).getEmotionTotal() / 3.3) - 9 * (person.getEmotions2().get(2).getEmotionTotal());
            matrix[2][3] = 81 * (person.getEmotions2().get(1).getEmotionTotal() / 3.3);

            rls = new ResolveLinearSystems(matrix);
            double[] resultNewPoint = rls.getResult();

            if (!Double.isNaN(resultOldPoint[0]) && !Double.isNaN(resultNewPoint[0])) {
                PersonPoint personPoint = new PersonPoint();
                personPoint.setOldState(resultOldPoint[0], resultOldPoint[1], resultOldPoint[2]);
                personPoint.setNewState(resultNewPoint[0], resultNewPoint[1], resultNewPoint[2]);
                personPoints.add(personPoint);
            }
        }

        float total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions1().get(0).getEmotionTotal();
        }
        result1.setNeutral(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions1().get(1).getEmotionTotal();
        }
        result1.setContempt(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions1().get(2).getEmotionTotal();
        }
        result1.setSurprise(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions1().get(3).getEmotionTotal();
        }
        result1.setEnjoyment(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions1().get(4).getEmotionTotal();
        }
        result1.setFear(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions1().get(5).getEmotionTotal();
        }
        result1.setInterest(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions1().get(6).getEmotionTotal();
        }
        result1.setAnger(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions1().get(7).getEmotionTotal();
        }
        result1.setShame(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions1().get(8).getEmotionTotal();
        }
        result1.setDistress(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions2().get(0).getEmotionTotal();
        }
        result2.setContempt(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions2().get(1).getEmotionTotal();
        }
        result2.setNeutral(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions2().get(2).getEmotionTotal();
        }
        result2.setSurprise(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions2().get(3).getEmotionTotal();
        }
        result2.setEnjoyment(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions2().get(4).getEmotionTotal();
        }
        result2.setFear(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions2().get(5).getEmotionTotal();
        }
        result2.setInterest(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions2().get(6).getEmotionTotal();
        }
        result2.setAnger(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions2().get(7).getEmotionTotal();
        }
        result2.setShame(total / persons.size());

        total = 0;
        for (int i = 0; i < persons.size(); i++) {
            total += persons.get(i).getEmotions2().get(8).getEmotionTotal();
        }
        result2.setDistress(total / persons.size());

        Answer answer = new Answer();
        answer.setAnswerMovieId(Integer.parseInt(movieId));
        answer.setQuestionAnger(-1 * result1.getAnger() + result2.getAnger());
        answer.setQuestionContempt((float) (-1 * (result1.getContempt() / 3.3) + (result2.getContempt() / 3.3)));
        answer.setQuestionDistress(-1 * result1.getDistress() + result2.getDistress());
        answer.setQuestionEnjoyment(-1 * result1.getEnjoyment() + result2.getEnjoyment());
        answer.setQuestionFear(-1 * result1.getFear() + result2.getFear());
        answer.setQuestionInterest(-1 * result1.getInterest() + result2.getInterest());
        answer.setQuestionNeutral(-1 * result1.getNeutral() + result2.getNeutral());
        answer.setQuestionShame(-1 * result1.getShame() + result2.getShame());
        answer.setQuestionSurprise(-1 * result1.getSurprise() + result2.getSurprise());

        answerDAO.save(answer);

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswer(answer);
        answerDTO.setPersonPoints(personPoints);

        return answerDTO;
    }

//    private ArrayList<Emotion> parseFirstEmotions(String line) {
//        ArrayList<Emotion> emotions = new ArrayList<>();
//        String cvsSplitBy = ",";
//
//        // use comma as separator
//        String[] parts = line.split(cvsSplitBy);
//
//        // Parse Neutral emotions
//        Emotion emotionNeutral = new Emotion();
//        emotionNeutral.setEmotionType(EmotionType.Neutral);
//        for (int j = 1; j < 10; j++) {
//            emotionNeutral.getEmotionValues().add(parseToNumber(parts[j]));
//            emotionNeutral.setEmotionTotal(emotionNeutral.getEmotionTotal() + parseToNumber(parts[j]));
//        }
//        emotions.add(emotionNeutral);
//        // Parse Contempt emotions
//        Emotion emotionFear = new Emotion();
//        emotionFear.setEmotionType(EmotionType.Fear);
//        for (int j = 10; j < 13; j++) {
//            emotionFear.getEmotionValues().add(parseToNumber(parts[j]));
//            emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[j]));
//        }
//        for (int j = 20; j < 23; j++) {
//            emotionFear.getEmotionValues().add(parseToNumber(parts[j]));
//            emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[j]));
//        }
//        for (int j = 31; j < 33; j++) {
//            emotionFear.getEmotionValues().add(parseToNumber(parts[j]));
//            emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[j]));
//        }
//        emotionFear.getEmotionValues().add(parseToNumber(parts[23]));
//        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() - parseToNumber(parts[23]));
//        emotionFear.getEmotionValues().add(parseToNumber(parts[33]));
//        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() - parseToNumber(parts[33]));
//        emotions.add(emotionFear);
//        // Parse Surprice emotions
//        Emotion emotionSurpise = new Emotion();
//        emotionSurpise.setEmotionType(EmotionType.Surprise);
//        emotionSurpise.getEmotionValues().add(parseToNumber(parts[13]));
//        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[13]));
//        emotionSurpise.getEmotionValues().add(parseToNumber(parts[24]));
//        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[24]));
//        emotionSurpise.getEmotionValues().add(parseToNumber(parts[34]));
//        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[34]));
//        emotions.add(emotionSurpise);
//        // Parse Enjoyment emotions
//        Emotion emotionEnjoyment = new Emotion();
//        emotionEnjoyment.setEmotionType(EmotionType.Enjoyment);
//        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[14]));
//        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[14]));
//        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[26]));
//        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[26]));
//        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[35]));
//        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[35]));
//        emotions.add(emotionEnjoyment);
//        // Parse Fear emotions
//        Emotion emotionContempt = new Emotion();
//        emotionContempt.setEmotionType(EmotionType.Contempt);
//        emotionContempt.getEmotionValues().add(parseToNumber(parts[15]));
//        emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[15]));
//        emotionContempt.getEmotionValues().add(parseToNumber(parts[25]));
//        emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[25]));
//        emotionContempt.getEmotionValues().add(parseToNumber(parts[36]));
//        emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[36]));
//        emotions.add(emotionContempt);
//        // Parse Interest emotions
//        Emotion emotionInterest = new Emotion();
//        emotionInterest.setEmotionType(EmotionType.Interest);
//        emotionInterest.getEmotionValues().add(parseToNumber(parts[16]));
//        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[16]));
//        emotionInterest.getEmotionValues().add(parseToNumber(parts[27]));
//        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[27]));
//        emotionInterest.getEmotionValues().add(parseToNumber(parts[37]));
//        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[37]));
//        emotions.add(emotionInterest);
//        // Parse Anger emotions
//        Emotion emotionAnger = new Emotion();
//        emotionAnger.setEmotionType(EmotionType.Anger);
//        emotionAnger.getEmotionValues().add(parseToNumber(parts[17]));
//        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[17]));
//        emotionAnger.getEmotionValues().add(parseToNumber(parts[28]));
//        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[28]));
//        emotionAnger.getEmotionValues().add(parseToNumber(parts[38]));
//        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[38]));
//        emotions.add(emotionAnger);
//        // Parse Shame emotions
//        Emotion emotionShame = new Emotion();
//        emotionShame.setEmotionType(EmotionType.Shame);
//        emotionShame.getEmotionValues().add(parseToNumber(parts[18]));
//        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[18]));
//        emotionShame.getEmotionValues().add(parseToNumber(parts[29]));
//        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[29]));
//        emotionShame.getEmotionValues().add(parseToNumber(parts[39]));
//        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[39]));
//        emotions.add(emotionShame);
//        // Parse Distress emotions
//        Emotion emotionDistress = new Emotion();
//        emotionDistress.setEmotionType(EmotionType.Distress);
//        emotionDistress.getEmotionValues().add(parseToNumber(parts[19]));
//        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[20]));
//        emotionDistress.getEmotionValues().add(parseToNumber(parts[30]));
//        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[30]));
//        emotionDistress.getEmotionValues().add(parseToNumber(parts[40]));
//        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[40]));
//        emotions.add(emotionDistress);
//
//        return emotions;
//    }

    private ArrayList<Emotion> parseFirstEmotions(String line) {
        ArrayList<Emotion> emotions = new ArrayList<>();
        String cvsSplitBy = ",";

        // use comma as separator
        String[] parts = line.split(cvsSplitBy);

        // Parse Neutral emotions
        Emotion emotionNeutral = new Emotion();
        emotionNeutral.setEmotionType(EmotionType.Neutral);
        for (int j = 1; j < 10; j++) {
            emotionNeutral.getEmotionValues().add(parseToNumber(parts[j]));
            emotionNeutral.setEmotionTotal(emotionNeutral.getEmotionTotal() + parseToNumber(parts[j]));
        }
        emotions.add(emotionNeutral);
        // Parse Contempt emotions
        Emotion emotionContempt = new Emotion();
        emotionContempt.setEmotionType(EmotionType.Contempt);
        emotionContempt.getEmotionValues().add(parseToNumber(parts[10]));
        emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() - parseToNumber(parts[10]));
        for (int j = 11; j < 13; j++) {
            emotionContempt.getEmotionValues().add(parseToNumber(parts[j]));
            emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[j]));
        }
        for (int j = 20; j < 25; j++) {
            emotionContempt.getEmotionValues().add(parseToNumber(parts[j]));
            emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[j]));
        }
        for (int j = 31; j < 34; j++) {
            emotionContempt.getEmotionValues().add(parseToNumber(parts[j]));
            emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[j]));
        }
        emotions.add(emotionContempt);
        // Parse Surprice emotions
        Emotion emotionSurpise = new Emotion();
        emotionSurpise.setEmotionType(EmotionType.Surprise);
        emotionSurpise.getEmotionValues().add(parseToNumber(parts[13]));
        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[13]));
        emotionSurpise.getEmotionValues().add(parseToNumber(parts[24]));
        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[24]));
        emotionSurpise.getEmotionValues().add(parseToNumber(parts[34]));
        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[34]));
        emotions.add(emotionSurpise);
        // Parse Enjoyment emotions
        Emotion emotionEnjoyment = new Emotion();
        emotionEnjoyment.setEmotionType(EmotionType.Enjoyment);
        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[14]));
        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[14]));
        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[26]));
        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[26]));
        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[35]));
        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[35]));
        emotions.add(emotionEnjoyment);
        // Parse Fear emotions
        Emotion emotionFear = new Emotion();
        emotionFear.setEmotionType(EmotionType.Fear);
        emotionFear.getEmotionValues().add(parseToNumber(parts[15]));
        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[15]));
        emotionFear.getEmotionValues().add(parseToNumber(parts[25]));
        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[25]));
        emotionFear.getEmotionValues().add(parseToNumber(parts[36]));
        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[36]));
        emotions.add(emotionFear);
        // Parse Interest emotions
        Emotion emotionInterest = new Emotion();
        emotionInterest.setEmotionType(EmotionType.Interest);
        emotionInterest.getEmotionValues().add(parseToNumber(parts[16]));
        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[16]));
        emotionInterest.getEmotionValues().add(parseToNumber(parts[27]));
        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[27]));
        emotionInterest.getEmotionValues().add(parseToNumber(parts[37]));
        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[37]));
        emotions.add(emotionInterest);
        // Parse Anger emotions
        Emotion emotionAnger = new Emotion();
        emotionAnger.setEmotionType(EmotionType.Anger);
        emotionAnger.getEmotionValues().add(parseToNumber(parts[17]));
        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[17]));
        emotionAnger.getEmotionValues().add(parseToNumber(parts[28]));
        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[28]));
        emotionAnger.getEmotionValues().add(parseToNumber(parts[38]));
        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[38]));
        emotions.add(emotionAnger);
        // Parse Shame emotions
        Emotion emotionShame = new Emotion();
        emotionShame.setEmotionType(EmotionType.Shame);
        emotionShame.getEmotionValues().add(parseToNumber(parts[18]));
        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[18]));
        emotionShame.getEmotionValues().add(parseToNumber(parts[29]));
        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[29]));
        emotionShame.getEmotionValues().add(parseToNumber(parts[39]));
        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[39]));
        emotions.add(emotionShame);
        // Parse Distress emotions
        Emotion emotionDistress = new Emotion();
        emotionDistress.setEmotionType(EmotionType.Distress);
        emotionDistress.getEmotionValues().add(parseToNumber(parts[19]));
        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[20]));
        emotionDistress.getEmotionValues().add(parseToNumber(parts[30]));
        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[30]));
        emotionDistress.getEmotionValues().add(parseToNumber(parts[40]));
        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[40]));
        emotions.add(emotionDistress);

        return emotions;
    }

//    private ArrayList<Emotion> parseSecondEmotions(String line) {
//        ArrayList<Emotion> emotions = new ArrayList<>();
//        String cvsSplitBy = ",";
//
//        // use comma as separator
//        String[] parts = line.split(cvsSplitBy);
//
//        // Parse Fear emotions
//        Emotion emotionFear = new Emotion();
//        emotionFear.setEmotionType(EmotionType.Fear);
//        for (int j = 1; j < 7; j++) {
//            emotionFear.getEmotionValues().add(parseToNumber(parts[j]));
//            emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[j]));
//        }
//        for (int j = 8; j < 10; j++) {
//            emotionFear.getEmotionValues().add(parseToNumber(parts[j]));
//            emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[j]));
//        }
//        emotionFear.getEmotionValues().add(parseToNumber(parts[7]));
//        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() - parseToNumber(parts[7]));
//        emotionFear.getEmotionValues().add(parseToNumber(parts[10]));
//        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() - parseToNumber(parts[10]));
//        emotions.add(emotionFear);
//        // Parse Contempt emotions
//        Emotion emotionNeutral = new Emotion();
//        emotionNeutral.setEmotionType(EmotionType.Neutral);
//        for (int j = 11; j < 14; j++) {
//            emotionNeutral.getEmotionValues().add(parseToNumber(parts[j]));
//            emotionNeutral.setEmotionTotal(emotionNeutral.getEmotionTotal() + parseToNumber(parts[j]));
//        }
//        for (int j = 21; j < 25; j++) {
//            emotionNeutral.getEmotionValues().add(parseToNumber(parts[j]));
//            emotionNeutral.setEmotionTotal(emotionNeutral.getEmotionTotal() + parseToNumber(parts[j]));
//        }
//        for (int j = 32; j < 34; j++) {
//            emotionNeutral.getEmotionValues().add(parseToNumber(parts[j]));
//            emotionNeutral.setEmotionTotal(emotionNeutral.getEmotionTotal() + parseToNumber(parts[j]));
//        }
//        emotions.add(emotionNeutral);
//        // Parse Surprice emotions
//        Emotion emotionSurpise = new Emotion();
//        emotionSurpise.setEmotionType(EmotionType.Surprise);
//        emotionSurpise.getEmotionValues().add(parseToNumber(parts[14]));
//        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[14]));
//        emotionSurpise.getEmotionValues().add(parseToNumber(parts[25]));
//        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[25]));
//        emotionSurpise.getEmotionValues().add(parseToNumber(parts[35]));
//        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[35]));
//        emotions.add(emotionSurpise);
//        // Parse Enjoyment emotions
//        Emotion emotionEnjoyment = new Emotion();
//        emotionEnjoyment.setEmotionType(EmotionType.Enjoyment);
//        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[15]));
//        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[15]));
//        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[27]));
//        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[27]));
//        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[36]));
//        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[36]));
//        emotions.add(emotionEnjoyment);
//        // Parse Fear emotions
//        Emotion emotionContempt = new Emotion();
//        emotionContempt.setEmotionType(EmotionType.Contempt);
//        emotionContempt.getEmotionValues().add(parseToNumber(parts[16]));
//        emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[16]));
//        emotionContempt.getEmotionValues().add(parseToNumber(parts[26]));
//        emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[26]));
//        emotionContempt.getEmotionValues().add(parseToNumber(parts[37]));
//        emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[37]));
//        emotions.add(emotionContempt);
//        // Parse Interest emotions
//        Emotion emotionInterest = new Emotion();
//        emotionInterest.setEmotionType(EmotionType.Interest);
//        emotionInterest.getEmotionValues().add(parseToNumber(parts[17]));
//        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[17]));
//        emotionInterest.getEmotionValues().add(parseToNumber(parts[28]));
//        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[28]));
//        emotionInterest.getEmotionValues().add(parseToNumber(parts[38]));
//        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[38]));
//        emotions.add(emotionInterest);
//        // Parse Anger emotions
//        Emotion emotionAnger = new Emotion();
//        emotionAnger.setEmotionType(EmotionType.Anger);
//        emotionAnger.getEmotionValues().add(parseToNumber(parts[18]));
//        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[18]));
//        emotionAnger.getEmotionValues().add(parseToNumber(parts[29]));
//        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[29]));
//        emotionAnger.getEmotionValues().add(parseToNumber(parts[39]));
//        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[39]));
//        emotions.add(emotionAnger);
//        // Parse Shame emotions
//        Emotion emotionShame = new Emotion();
//        emotionShame.setEmotionType(EmotionType.Shame);
//        emotionShame.getEmotionValues().add(parseToNumber(parts[19]));
//        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[19]));
//        emotionShame.getEmotionValues().add(parseToNumber(parts[30]));
//        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[30]));
//        emotionShame.getEmotionValues().add(parseToNumber(parts[40]));
//        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[40]));
//        emotions.add(emotionShame);
//        // Parse Distress emotions
//        Emotion emotionDistress = new Emotion();
//        emotionDistress.setEmotionType(EmotionType.Distress);
//        emotionDistress.getEmotionValues().add(parseToNumber(parts[20]));
//        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[20]));
//        emotionDistress.getEmotionValues().add(parseToNumber(parts[31]));
//        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[31]));
//        emotionDistress.getEmotionValues().add(parseToNumber(parts[41]));
//        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[41]));
//        emotions.add(emotionDistress);
//
//        return emotions;
//    }

    private ArrayList<Emotion> parseSecondEmotions(String line) {
        ArrayList<Emotion> emotions = new ArrayList<>();
        String cvsSplitBy = ",";

        // use comma as separator
        String[] parts = line.split(cvsSplitBy);

        // Parse Neutral emotions
        Emotion emotionContempt = new Emotion();
        emotionContempt.setEmotionType(EmotionType.Contempt);
        emotionContempt.getEmotionValues().add(parseToNumber(parts[1]));
        emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() - parseToNumber(parts[1]));
        for (int j = 2; j < 11; j++) {
            emotionContempt.getEmotionValues().add(parseToNumber(parts[j]));
            emotionContempt.setEmotionTotal(emotionContempt.getEmotionTotal() + parseToNumber(parts[j]));
        }
        emotions.add(emotionContempt);
        // Parse Contempt emotions
        Emotion emotionNeutral = new Emotion();
        emotionNeutral.setEmotionType(EmotionType.Neutral);
        for (int j = 11; j < 14; j++) {
            emotionNeutral.getEmotionValues().add(parseToNumber(parts[j]));
            emotionNeutral.setEmotionTotal(emotionNeutral.getEmotionTotal() + parseToNumber(parts[j]));
        }
        for (int j = 21; j < 25; j++) {
            emotionNeutral.getEmotionValues().add(parseToNumber(parts[j]));
            emotionNeutral.setEmotionTotal(emotionNeutral.getEmotionTotal() + parseToNumber(parts[j]));
        }
        for (int j = 32; j < 34; j++) {
            emotionNeutral.getEmotionValues().add(parseToNumber(parts[j]));
            emotionNeutral.setEmotionTotal(emotionNeutral.getEmotionTotal() + parseToNumber(parts[j]));
        }
        emotions.add(emotionNeutral);
        // Parse Surprice emotions
        Emotion emotionSurpise = new Emotion();
        emotionSurpise.setEmotionType(EmotionType.Surprise);
        emotionSurpise.getEmotionValues().add(parseToNumber(parts[14]));
        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[14]));
        emotionSurpise.getEmotionValues().add(parseToNumber(parts[25]));
        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[25]));
        emotionSurpise.getEmotionValues().add(parseToNumber(parts[35]));
        emotionSurpise.setEmotionTotal(emotionSurpise.getEmotionTotal() + parseToNumber(parts[35]));
        emotions.add(emotionSurpise);
        // Parse Enjoyment emotions
        Emotion emotionEnjoyment = new Emotion();
        emotionEnjoyment.setEmotionType(EmotionType.Enjoyment);
        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[15]));
        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[15]));
        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[27]));
        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[27]));
        emotionEnjoyment.getEmotionValues().add(parseToNumber(parts[36]));
        emotionEnjoyment.setEmotionTotal(emotionEnjoyment.getEmotionTotal() + parseToNumber(parts[36]));
        emotions.add(emotionEnjoyment);
        // Parse Fear emotions
        Emotion emotionFear = new Emotion();
        emotionFear.setEmotionType(EmotionType.Fear);
        emotionFear.getEmotionValues().add(parseToNumber(parts[16]));
        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[16]));
        emotionFear.getEmotionValues().add(parseToNumber(parts[26]));
        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[26]));
        emotionFear.getEmotionValues().add(parseToNumber(parts[37]));
        emotionFear.setEmotionTotal(emotionFear.getEmotionTotal() + parseToNumber(parts[37]));
        emotions.add(emotionFear);
        // Parse Interest emotions
        Emotion emotionInterest = new Emotion();
        emotionInterest.setEmotionType(EmotionType.Interest);
        emotionInterest.getEmotionValues().add(parseToNumber(parts[17]));
        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[17]));
        emotionInterest.getEmotionValues().add(parseToNumber(parts[28]));
        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[28]));
        emotionInterest.getEmotionValues().add(parseToNumber(parts[38]));
        emotionInterest.setEmotionTotal(emotionInterest.getEmotionTotal() + parseToNumber(parts[38]));
        emotions.add(emotionInterest);
        // Parse Anger emotions
        Emotion emotionAnger = new Emotion();
        emotionAnger.setEmotionType(EmotionType.Anger);
        emotionAnger.getEmotionValues().add(parseToNumber(parts[18]));
        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[18]));
        emotionAnger.getEmotionValues().add(parseToNumber(parts[29]));
        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[29]));
        emotionAnger.getEmotionValues().add(parseToNumber(parts[39]));
        emotionAnger.setEmotionTotal(emotionAnger.getEmotionTotal() + parseToNumber(parts[39]));
        emotions.add(emotionAnger);
        // Parse Shame emotions
        Emotion emotionShame = new Emotion();
        emotionShame.setEmotionType(EmotionType.Shame);
        emotionShame.getEmotionValues().add(parseToNumber(parts[19]));
        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[19]));
        emotionShame.getEmotionValues().add(parseToNumber(parts[30]));
        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[30]));
        emotionShame.getEmotionValues().add(parseToNumber(parts[40]));
        emotionShame.setEmotionTotal(emotionShame.getEmotionTotal() + parseToNumber(parts[40]));
        emotions.add(emotionShame);
        // Parse Distress emotions
        Emotion emotionDistress = new Emotion();
        emotionDistress.setEmotionType(EmotionType.Distress);
        emotionDistress.getEmotionValues().add(parseToNumber(parts[20]));
        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[20]));
        emotionDistress.getEmotionValues().add(parseToNumber(parts[31]));
        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[31]));
        emotionDistress.getEmotionValues().add(parseToNumber(parts[41]));
        emotionDistress.setEmotionTotal(emotionDistress.getEmotionTotal() + parseToNumber(parts[41]));
        emotions.add(emotionDistress);

        return emotions;
    }

    // Parse string answer to number.
    private Integer parseToNumber(String string) {
        if (string.contains("-3")) {
            return -3;
        } else if (string.contains("-2")) {
            return -2;
        } else if (string.contains("-1")) {
            return -1;
        } else if (string.contains("0")) {
            return 0;
        } else if (string.contains("1")) {
            return 1;
        } else if (string.contains("2")) {
            return 2;
        } else if (string.contains("3")) {
            return 3;
        }

        return -0;
    }

}
