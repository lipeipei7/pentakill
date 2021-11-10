//单行注释
/*
* src: 资源
* main: 主要
*/
/*
* 多行注释
* public: 公开
*   - default: 默认
*   - protected: 保护
*   - private: 私密
* class: 类
* Puzzle: 题 (大写P)
* int: integer - 整数
* String: 字符串 (大写S)
* char: 字符
* letter: 字母
* concat: 向后插入
*/

// 类
public class Puzzle {
    // field: 项 (变量)

    // function: 函数
    public static void main(String[] args) {
        // variable: 变量
       char var = '7';
       String email = "li.pei@outlook.com";

        // 打印到输出框
        System.out.println(email);

        // 方法一
        // 1. email 分成两部分
        String[] parts = email.split("@");
        // 2. 插入7
        // 3. 组合3个部分
        String result1 = parts[0]
                .concat(String.valueOf(var))
                .concat("@")
                .concat(parts[1]);

        System.out.printf("result 1: %s\n", result1);

        // 方法二
        // 1. 直接插入到位置6
        char[] letters = email.toCharArray();
        String result2 = "";

        for (int i = 0; i < letters.length; i++) {
            if (i == 6) {
                result2 = result2.concat(String.valueOf(var));
            }
            result2 = result2.concat(String.valueOf(letters[i]));
        }

        System.out.printf("result 2: %s\n", result2);


        // 方法三
        StringBuilder result3 = new StringBuilder(email);
        int index = result3.indexOf("@");
        result3.insert(index, var);

        System.out.printf("result 3: %s\n", result3);

    }
}
