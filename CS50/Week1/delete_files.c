#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "dirent.h"


void delete_files();
void delete_files_in_path(char path[500]);

int main (void) {
	delete_files();
}

void delete_files() {
	int size = 8;
	//input some file paths to deletes
	char *paths[10];
	paths[0] = "C:\\Users\\ngoclm6\\AppData\\Local\\LAN Messenger\\LAN Messenger\\logs";
	paths[1] = "C:\\Users\\ngoclm6\\AppData\\Local\\Temp";
	paths[2] = "C:\\Users\\ngoclm6\\AppData\\Local\\Apps";
	//paths[3] = "C:\\Users\\ngoclm6\\AppData\\Local\\Google";
	paths[3] = "C:\\Users\\ngoclm6\\AppData\\Local\\javasharedresources";
	paths[4] = "C:\\Users\\ngoclm6\\AppData\\Local\\Microsoft";
	paths[5] = "C:\\Users\\ngoclm6\\AppData\\Local\\Mozilla";
	paths[6] = "C:\\Users\\ngoclm6\\AppData\\Local\\Mozilla Firefox";
	paths[7] = "C:\\Users\\ngoclm6\\AppData\\Local\\NetBeans";
	//browse every paths
	for (int i = 0; i < size; i++) {
		//deletes every files in each path
		delete_files_in_path (paths[i]);
	}
		
}

void delete_files_in_path (char path[500]) {
	//list all file names in path
	DIR *dir;
    struct dirent *ent;
	dir = opendir (path);
	char* tmp = malloc(500 * sizeof(char));
	strcpy(tmp, path);
	
    if (dir != NULL) {
		while ((ent = readdir (dir)) != NULL) {
			// ignore . and ..
			if (!strcmp(ent->d_name,".") || !strcmp(ent->d_name,"..")) continue;
        switch (ent->d_type) {
        case DT_REG:
			//concat file name to path
			strcat(tmp, "\\");
			strcat(tmp, ent->d_name);
			remove(tmp);
			break;

        case DT_DIR:
		//concat file name to path
			strcat(tmp, "\\");
			strcat(tmp, ent->d_name);
			//delete file with full path
			delete_files_in_path(tmp);
			break;
        default:
			break;
        }
		//reset tmp to raw path
		strcpy(tmp, path);
      }
      closedir (dir);
	  free(tmp);
	}
	
	
	
}