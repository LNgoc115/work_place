#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "dirent.h"


void delete_files();
void delete_files_in_path(char path[100]);

int main (void) {
	delete_files();
}

void delete_files() {
	int size = 2;
	//input some file paths to deletes
	char paths[5][100] = {"C:\\Users\\ngoclm6\\AppData\\Local\\LAN Messenger\\LAN Messenger\\logs", "C:\\Users\\ngoclm6\\AppData\\Local\\Temp"};
	//browse every paths
	
	for (int i = 0; i < size; i++) {
		//deletes every files in each path
		delete_files_in_path (paths[i]);
	}
		
}

void delete_files_in_path (char path[100]) {
	//list all file names in path
	DIR *dir;
    struct dirent *ent;
	dir = opendir (path);
	char* tmp = malloc(100 * sizeof(char));
	strcpy(tmp, path);
	
    if (dir != NULL) {
		while ((ent = readdir (dir)) != NULL) {
        switch (ent->d_type) {
        case DT_REG:
		//concat file name to path
			strcat(tmp, "\\");
			strcat(tmp, ent->d_name);
			//delete file with full path
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
      }

      closedir (dir);
	}
	
	
	
}