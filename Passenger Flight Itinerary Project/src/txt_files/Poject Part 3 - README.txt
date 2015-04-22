This zip contains files that facilitate writing your own tests for Stage 3.
Unfortunately, I can't think of a reasonable way to write something that
directly tests the results of an export without handing you part of your
Stage 3 code.

You'll note that there's a new example file - "CustomDataTest.txt" - whose
original data specification can be found in a method at the top of both of 
the new Java files.  Running "GeneralExportMain" will recreate this file if
left unedited, and the version in "GeneralImportTest" is used for comparison
to test your importing functions.  Of course, you can't import things well
if the export's broken, so together the two should help in further testing 
your Stage 3s.

If you want to write your own tests, backup the current "CustomDataTest" and
write your own setup for the Flights and Passengers in that initialization 
method at the top.  Make sure the code is copy-and-pasted between both
"GeneralExportMain" and "GeneralImportTest," and that all objects were added
to the relevant lists, and you can make your own custom tests that way.

