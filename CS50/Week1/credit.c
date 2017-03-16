#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

void credit();
char* get_card_number();
void check_valid_card_number(char* number);
char* get_string();
int check_valid_number(char* number);
int check_sum(char* number);
void check_card_type(char* number);
int multiply(int i);

int main (void) {
	credit();
	
}

void credit() {
	// get card numbers from console
	char* number = get_card_number();
	
	// print result of checking if the card number is valid or not and which types of it
	check_valid_card_number(number);
}

void check_valid_card_number(char* number) {
	// check sum first
	int c_sum = check_sum(number);
	
	if (!c_sum) {
		printf("INVALID\n");
		return;
	} 
	// if check sum is ok , then check type of card
	check_card_type (number);
}

void check_card_type(char* number) {
	switch(number[0]) {
		case '3':
			if (number[1] == '4' || number[1] == '7') {
				printf("AMEX\n");
			} else {
				printf("Unrelize\n");
			}
			break;
		case '4':
			printf("VISA\n");
			break;
		case '5':
			if (number[1]=='1' || number[1]=='2' || number[1]=='3' || number[1]=='4' || number[1]=='5') {
				printf("MASTERCARD\n");
			} else {
				printf("Unrelize\n");
			}
			break;
		default:
			printf("Unrelize\n");
			break;
 	}
}

char* get_card_number() {
	char* number;
	do {
		// get string from console
		printf("Number: ");
		number = get_string();
		// remove the last character in string '\n'
		size_t length = strlen(number);
		if (length >= 1) number[length - 1] = 0;
		// check if each character is number	
	} while (!check_valid_number(number));
	
	return number;
}

int check_valid_number(char* number) {
	if (number == NULL || strlen(number) == 0) {
		return 0;
	}
	for (int i = 0; i < strlen(number); i++) {
		char c = number[i];
		if (!isdigit(c)) {
			return 0;
		}
	}
	return 1;
}

char* get_string() {
	char tmp[255];
	char *string = (char*)malloc(255);
	fgets(tmp, 255, stdin);
	strcpy(string, tmp);
	return string;
}

int check_sum(char* number) {
	int sum = 0;
	int digit = 0;
	// duyet theo thu tu 2 4 6 8
	for (int i = 0; i < strlen(number); i++) {
		//convert character to int
		digit = number[i] - '0';
		// neu la cac chu so 1 3 5 ...
		if (i % 2 == 0) {
			sum += digit;
		} else { // neu la cac chu so 2 4 6 ...
			sum += multiply(digit);
		}
	}
	
	//neu (sum1 + sum2) % 10 = 0 return 1 else return 0
	if (sum % 10 == 0) return 1; else return 0;
}

int multiply(int i) {
	i*=2;
	return ((i / 10) + (i % 10));
}