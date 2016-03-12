This is the recorded autonomous data.

We need to put this on the other robot and be sure to test it at least once.

Here is how to do that:

 - On competition robot, use an FTP program (FileZilla is what I used) and get into "sftp://roborio-2832-frc.local"
 - Navigate to home/lvuser
 - In the lvuser directory, put both index.txt and all of the *.dat files (eg CornerAuton.dat)
   - These are located in the same directory as this file
 - YAY! now the files are on the competition robot

 Now you need to test it:
 - On the SmartDashboard, ensure "Use Recordable Auton" checkbox is checked.
    - This overrides regular auton and tells the robot to use recorded.  TO GO BACK TO OLD AUTON, UNCHECK THIS CHECKBOX
 - On the smart dashboard, a little thing should pop up labeled "RecordableAutonIndex" or something
 - Select the autonomous you want to test (CornerAuton)
 - Enable autonomous mode, watch it do the stuff
  MAKE SURE YOU ARE READY TO DISABLE, RESULTS MAY VARY

IF YOU HAVE ANY QUESTIONS, TALK TO BRENDAN