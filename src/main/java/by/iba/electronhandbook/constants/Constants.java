package by.iba.electronhandbook.constants;

public final class Constants {

    public static final String GET_ALL_USERS = "SELECT `USER_ID`, `PASSWORD`, `ROLE` FROM `db_handbook`.`users`";
    public static final String ADD_USER = "INSERT INTO `db_handbook`.`users` (`PASSWORD`,`ROLE`) VALUES (?,?)";
    public static final String DELETE_USER = "DELETE FROM `db_handbook`.`users` WHERE `USER_ID`=?";
    public static final String GET_USER = GET_ALL_USERS.concat(" WHERE `users`.`USER_ID`=?");
    public static final String UPDATE_USER = "UPDATE `db_handbook`.`users` SET `PASSWORD`=?, `ROLE`=? WHERE `USER_ID`=?";

    public static final String GET_ALL_STUDENTS = "SELECT `ID`,`FIRST_NAME`, `SECOND_NAME`, `students`.`AVG_MARK`, `students`.`GROUP_NUMBER`, `groups`.`AVG_MARK` as `GROUP_AVG_MARK`" +
            "FROM `db_handbook`.`students`" +
            "LEFT OUTER JOIN `db_handbook`.`groups`" +
            "ON `students`.`GROUP_NUMBER` = `groups`.`GROUP_NUMBER`";
    public static final String ADD_STUDENT = "INSERT INTO `db_handbook`.`students` (`FIRST_NAME`, `SECOND_NAME`,`AVG_MARK`, `GROUP_NUMBER`) VALUES (?,?,?,?)";
    public static final String DELETE_STUDENT = "DELETE FROM `db_handbook`.`students` WHERE `ID`=?";
    public static final String UPDATE_STUDENT = "UPDATE `db_handbook`.`students` SET `FIRST_NAME`=?, `SECOND_NAME`=?,`AVG_MARK`=?, `GROUP_NUMBER`=? WHERE `ID`=?";
    public static final String GET_STUDENT = GET_ALL_STUDENTS.concat(" WHERE `room`.`ID`=?");

    public static final String GET_ALL_GROUPS = "SELECT `GROUP_NUMBER`, `AVG_MARK` FROM `db_handbook`.`groups`";
    public static final String ADD_GROUP = "INSERT INTO `db_handbook`.`groups` (`AVG_MARK`) VALUES (?)";
    public static final String DELETE_GROUP = "DELETE FROM `db_handbook`.`groups` WHERE `GROUP_NUMBER`=?";
    public static final String UPDATE_GROUP = "UPDATE `db_handbook`.`groups` SET `AVG_MARK`=? WHERE `GROUP_NUBER`=?";
    public static final String GET_GROUP = GET_ALL_GROUPS.concat(" WHERE `GROUP_NUMBER`=?");

    public static final String GET_ALL_PROFESSORS = "SELECT `ID`, `FIRST_NAME`, `SECOND_NAME`, `FATHER_NAME`, `BIRTH_DATE`, `AVG_MARK` FROM `db_handbook`.`professors`";
    public static final String ADD_PROFESSOR = "INSERT INTO `db_handbook`.`professors` (`FIRST_NAME`, `SECOND_NAME`, `FATHER_NAME`, `BIRTH_DATE`, `AVG_MARK`) VALUES (?,?,?,?,?)";
    public static final String DELETE_PROFESSOR = "DELETE FROM `db_handbook`.`professors` WHERE `ID`=?";
    public static final String UPDATE_PROFESSOR = "UPDATE `db_handbook`.`professors` SET `FIRST_NAME`=?, `SECOND_NAME`=?, `FATHER_NAME`=?, `BIRTH_DATE`=?, `AVG_MARK`=? WHERE `ID`=?";
    public static final String GET_PROFESSOR = GET_ALL_PROFESSORS.concat(" WHERE `ID`=?");

    public static final String GET_ALL_STUDIES = "SELECT `studies`.`ID` , `NAME`, `HOURS`, `studies`.`AVG_MARK` as `STUDY_AVG_MARK`, " +
            "`professors`.`ID` as `PROFESSOR_ID`, `FIRST_NAME`, `SECOND_NAME`, `FATHER_NAME`, `BIRTH_DATE`, `professors`.`AVG_MARK` as `PROFESSOR_AVG_MARK` " +
            "FROM (`db_handbook`.`studies`" +
            "LEFT OUTER JOIN `db_handbook`.`professors`" +
            "ON `studies`.`PROFESSOR_ID` = `professors`.`ID`)";
    public static final String ADD_STUDY = "INSERT INTO `db_handbook`.`studies` (`NAME`, `HOURS`, `PROFESSOR_ID`, `AVG_MARK`) VALUES (?,?,?,?)";
    public static final String DELETE_STUDY = "DELETE FROM `db_handbook`.`studies` WHERE `studies`.`ID`=?";
    public static final String UPDATE_STUDY = "UPDATE `db_handbook`.`studies` SET `NAME`=?, `HOURS`=?, `PROFESSOR_ID`=?, `AVG_MARK`=? WHERE `ID`=?";
    public static final String GET_STUDY = GET_ALL_STUDIES.concat(" WHERE `ID`=?");

    public static final String GET_ALL_MARKS = "SELECT `marks`.`ID` as `MARK_ID`, `DATE`, `MARK`, `COMMENTS`, " +
            "`studies`.`ID` as `STUDY_ID`, `studies`.`NAME`, `studies`.`HOURS`, `studies`.`AVG_MARK` as `STUDY_AVG_MARK`, " +
            "`students`.`ID` as `STUDENT_ID`, `students`.`FIRST_NAME`, `students`.`SECOND_NAME`, `students`.`AVG_MARK` as `STUDENT_AVG_MARK`, `students`.`GROUP_NUMBER`, " +
            "`groups`.`AVG_MARK` as `GROUP_AVG_MARK`, " +
            "`professors`.`ID` as `PROFESSOR_ID`, `professors`.`FIRST_NAME` as `PROFESSOR_FIRST_NAME`, `professors`.`SECOND_NAME` as `PROFESSOR_SECOND_NAME`, `professors`.`FATHER_NAME`, `professors`.`BIRTH_DATE`, `professors`.`AVG_MARK` as `PROFESSOR_AVG_MARK`" +
            "FROM ((((`db_handbook`.`marks` " +
            "LEFT OUTER JOIN `db_handbook`.`studies` " +
            "ON `marks`.`STUDY_ID` = `studies`.`ID`) " +
            "LEFT OUTER JOIN `db_handbook`.`students` " +
            "ON `marks`.`STUDENT_ID` = `students`.`ID`) " +
            "LEFT OUTER JOIN `db_handbook`.`groups` " +
            "ON `students`.`GROUP_NUMBER` = `groups`.`GROUP_NUMBER`) " +
            "LEFT OUTER JOIN `db_handbook`.`professors` " +
            "ON `marks`.`PROFESSOR_ID` = `professors`.`ID`)";
    public static final String ADD_MARK = "INSERT INTO `db_handbook`.`marks` (`STUDY_ID`, `STUDENT_ID`, `DATE`, `PROFESSOR_ID`, `MARK`, `COMMENTS`) VALUES (?,?,?,?,?,?)";
    public static final String DELETE_MARK = "DELETE FROM `db_handbook`.`marks` WHERE `marks`.`ID`=?";
    public static final String UPDATE_MARK = "UPDATE `db_handbook`.`marks` SET `STUDY_ID`=?, `STUDENT_ID`=?, `DATE`=?,`PROFESSOR_ID`=?, `MARK`=?, `COMMENTS`=? WHERE `ID`=?";
    public static final String GET_MARK = GET_ALL_MARKS.concat(" WHERE `ID`=?");
}