package tiiehenry.code.language.c;


import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.LanguageCFamily;
import tiiehenry.code.language.cpp.CppLexTask;

public class CLanguage extends LanguageCFamily {
    private static class SingletonInner {
        private static Language language = new CLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }
    @Override
    public String getName() {
        return "C";
    }
    private final static String[] keywords = {
            "char", "double", "float", "int", "long", "short", "void",
            "auto", "const", "extern", "register", "static", "volatile",
            "signed", "unsigned", "sizeof", "typedef",
            "enum", "struct", "union",
            "break", "case", "continue", "default", "do", "else", "for",
            "goto", "if", "return", "switch", "while",
            "define","include","ifdef","endif","ifndef","error","elif","line","pragma","undef"
    };
    private  final  static  String[] functions={
            "abort","abs","acos","asctime","asin","assert","atan","atan2","atexit","atof","atoi","atol"
            ,"bsearch","calloc","ceil","clearerr","clock","cos","cosh","ctime","difftime","div"
            ,"exit","exp","fabs","fclose","feof","ferror","fflush","fgetc","fgetpos","fgets","floor"
            ,"fmod","fopen","fprintf","fputc","fputs","fread","free","freopen","frexp","fscanf","fseek","fsetpos","ftell","fwrite"
            ,"getc","getchar","getenv","gets","gmtime","isalnum","isalpha","iscntrl","isdigit","isgraph","islower","isprint","ispunct","isspace","isupper","isxdigit","labs","ldexp","ldiv","localtime","log","log10","longjmp"
            ,"main","malloc","memchr","memcmp","memcpy","memmove","memset","mktime","modf","perror","pow","printf"
            ,"putc","putchar","puts","qsort","raise","rand","realloc","remove","rename","rewind"
            ,"scanf","setbuf","setjmp","setvbuf","signal","sin","sinh","sprintf","sqrt","srand","sscanf","strcat","strchr","strcmp","strcoll","strcpy","strcspn","strerror","strftime","strlen","strncat","strncmp","strncpy","strpbrk","strrchr","strspn","strstr","strtod","strtok","strtol","strtoul","strxfrm","system"
            ,"tan","tanh","time","tmpfile","tmpnam","tolower","toupper","ungetc","va_arg","vprintf","vfprintf"
            ,"__LINE__","__FILE__","__DATE__","__TIME__","_cplusplus","__STDC__"

    };
    private  final  static  String[] header={
            "math.h","stdio.h","stdlib.h","string.h","time.h","errno.h","ctype.h","local.h"
    };
    private final static char[] BASIC_C_OPERATORS = {
            '(', ')', '{', '}', '.', ',', ';', '=', '+', '-',
            '/', '*', '&', '!', '|', ':', '[', ']', '<', '>',
            '?', '~', '%', '^'
    };
    private CLanguage(){
//        setOperators(BASIC_C_OPERATORS);
        setKeywords(keywords);
        setInternalFuncs(functions);//先setName才能addName
        addInternalFunc(header);
    }
    @Override
    public LexTask newLexTask() {
        return new CppLexTask(this);
    }
}
