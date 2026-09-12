//Question 1
import java.util.Scanner;

public class Main {

static void checkPinLength(String pin) {

if (pin.length() != 4) {
System.out.print1n(
x: "Invalid PIN - must be exactly 4 digits."

else {
System.out.println(x: "PIN length OK.");

Run | Debug
public static void main(String[] args) {

Scanner sc = new Scanner(System.in);

System.out.print(s: "Enter PIN: ");
String pin = sc.nextLine();

checkPinLength(pin);

sc.close();

//Question 2
import java.util.Scanner;

public class Main

static String reverseEachWord(String sentence) {

String[] words = sentence.split(regex: " ");

StringBuilder result = new StringBuilder();

for (int i = 0; i < words.length; i++) {

StringBuilder word =
new StringBuilder(words[i]);

result.append(word.reverse());

if (i < words.length - 1) {
result.append(str: " ");

return result.toString();

Run | Debug
public static void main(String[] args) {

Scanner sc = new Scanner(System.in);

System.out.print(s: "Enter sentence: ");
String sentence = sc.nextLine();

System.out.print1n(reverseEachWord(sentence));

sc.close();

//Question 3
import java.util.Scanner;

public class Main

static void parseInventoryRecord(String csvLine) {

String[] data = csvLine.split(regex: ",");

if (data.length != 3) {
System.out.print1n(x: "Invalid Record");
return;

System.out.println("Product: " + data[0]
SKU: " + data[1]
Qty: " + data[2]);

Run | Debug
public static void main(String[] args) {

Scanner sc = new Scanner(System.in);

System.out.print(s: "Enter inventory record: ");
String csvLine = sc.nextLine();

parseInventoryRecord(csvLine);

sc.close();

//Question 4
import java.util.Scanner;

oublic class Main {

static String normalizeCode(String raw) {

raw - raw.trim();

if (raw.length() < 3) {
return raw;

return raw.substring(beginIndex: 8, endIndex: 3).toUpperCasc()
+ raw.substring(beginIndex: 3);

static String validatcAndFormat(String code) {

(code.length() 1- 13) {
return "Invalid: wrong length";

for (int i - 8; i < 3; i++) 0

if (!Character.isLetter(code.charAt(i))) {
return "Invalid: publisher code must be 3 letters";

for (int i - 3; i < 13; i++) {

if (!Character.isDigit(code.charAt(i))) {
return "Invalid: body must contain only digits";

String publisher - code.substring(beginIndex: 8, endIndex: 3);
String year - code.substring(beginIndex: 3, endIndex: 7);
String catalog - code.substring(beginIndex: 7);

StringBuilder sb - new StringBuilder();

sb.append(str: '[")
.append(publisher)
.append(str: "] YEAR: *)
.append(year)
.append(str: " | CATALOG: ")
.append(catalog);

return sb.tostring();

Ru | Debug
public static void main(String[] args) {

Scanner sc - new Scanner(Systen.in);

System.out.print(s: "Enter ISBN-style code: ");
String raw - sc.nextLinc();

String normalized - normalizcCode(raw);

System.out.print1n(validatcAndFormat(normalized));

sc.close();

//Question 5
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {

static void printFilteredwordFrequency(String feedback) f

feedback - feedback. toLowerCase();
feedback - feedback.replace(target:
feedback - feedback.replace( target:"
String[] words = feedback.split(regex: "\\s+");

String[] stopWords - {
"the", "was",

replacement: "*);
replacenent: "");

"and", "a", "is", "of", "in"

HashMap<String, Integer> map - new HashMapc>();
for (String word : words) {

boolcan isStopWord - false;

for (String stopWord : stopWords) {

if (word.cquals(stopWord)) {
isStopWord - truc;
break;

if (lisStopWord) {

(map.containsKey(word)) {
map.put(word, map.get(word) + 1);

else {
map.put(word, valuc: 1);

ArrayList<String> list .
new ArrayList<>(map.keySet());
list.sort((a, b) -> map.get(b) - map.gct(a));
for (String word : list) {
System.out.print1n(
word + ": " + map. get(word)

Ru | Debug
public static void main(String[] args) {

Scanner sc - new Scanner(Systen.in);

System.out.print(s: "Enter feedback: ");
String feedback - sc.nextLine();

printFilteredWordFrequency(feedback);

sc.close();
  
