//Question 1
import java.util.Scanner;

public class Main{
  static void countVowelsAndConsonants(String text) {
    int vowels = 0;
    int consonants = 0;

    for (int i = 0; i < text.length(); i++) {
      char ch = Character.toLowerCase(text.charAt(i));

    if (ch == 'a' || ch == 'e' | | ch == 'i' || ch == 'o' | | ch == 'u') {
      vowels++;

    else if (ch != ' ') {
      consonants++;

    System.out.println("Vowels: " + vowels);
    System.out.println("Consonants: " + consonants);

  }
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.print(s: "Enter text: ");
    String text = sc.nextLine();

    countVowelsAndConsonants(text);

    sc.close();
  }
}
//Question 2
import java.util.Scanner;

public class Main {
static void parseStudentRecord(String csvLine) {
String[] data = csvLine.split(regex: ",");

if (data.length != 3) {
System.out.println(x: "Invalid Record");
return;

System.out.println("Name: " + data[0]
Roll No: " + data [1]
Dept: " + data[2]);

Run | Debug
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print(s: "Enter student record: ");
String csvLine = sc.nextLine();
parseStudentRecord(csvLine);
sc.close();
//Question 3
import java.util.Scanner;
public class Main
static String validateFileExtension(String filename) {

int dot = filename.lastIndexOf(ch: '.');

if (dot == -1) {
return "Rejected - invalid file type";

String extension = filename.substring(dot + 1);
if (extension.equalsIgnoreCase(anotherString: "pdf") ||
extension.equalsIgnoreCase(anotherString: "docx") ||
extension.equalsIgnoreCase(anotherString: "zip")) {

return "Accepted";

return "Rejected - invalid file type";

Run | Debug
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);

System.out.print(s: "Enter filename: ");
String filename = sc.nextLine();

System.out.println(validateFileExtension(filename));

sc.close();
//Question 4
import java.util.Scanner;

public class Main

static String maskPhoneNumber(String phone) {

if (phone.length() != 10) {
return "Invalid phone number";

for (int i = 0; i < phone.length(); i++) {

if (!Character.isDigit(phone.charAt(i))) {
return "Invalid phone number";

StringBuilder sb = new StringBuilder(str: "XXXXXX");

sb. insert(offset: 6, str: "-");

sb.append(phone.substring(beginIndex: 6));

return sb.toString();

Run | Debug
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print(s: "Enter phone number: ");
String phone = sc.nextLine();
System.out.print1n(maskPhoneNumber (phone));
sc.close();
//Question 5
import java.util.Scanner;

public class Main
static String normalizeReference(String raw) {

raw = raw. trim();

if (raw.length() < 3) {
return raw;

return raw.substring(beginIndex: 0, endIndex: 3).toUpperCase()
+ raw.substring(beginIndex: 3);

static String validateAndFormat(String reference) {

if (reference.length() != 14) {
return "Invalid: wrong length";

for (int i = @; i < 3; i++) {

if (!Character.isLetter(reference.charAt(i))) {
return "Invalid: bank code must be 3 letters";

for (int i = 3; i < 14; i++) {

if (!Character.isDigit(reference.charAt(i))) {
return "Invalid: body must contain only digits";

String bank = reference.substring(beginIndex: 0, endIndex: 3);
String date = reference.substring(beginIndex: 3, endIndex: 9);
String sequence = reference.substring(beginIndex: 9);

StringBuilder sb = new StringBuilder();
sb. append(str: "[")
.append(bank)
.append(str: "] DATE: ")
.append(date.substring(beginIndex: 0, endIndex: 2))
.append(str: "/")
.append(date.substring(beginIndex: 2, endIndex: 4))
.append(str: "/")
.append(date.substring(beginIndex: 4))
.append(str: " | SEQ: ")
.append(sequence) ;
return sb.toString();

Run | Debug
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.print(s: "Enter transaction reference: ");
String raw = sc.nextLine();
String normalized = normalizeReference(raw);
System.out.printn(validateAndFormat(normalized));
sc.close();

