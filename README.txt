In fact, this the short introduction of what is what in that simple app:

1. Initializer class - has DataLoader, who loads quiz and keyAnswers, asks TestPerformerImpl to perform test process.
    Data loader has ScanReader to read CSV, and QUIZ, who has routes to quiz and answer resources;

2. TestPerformerImpl - has a Teacher class to test student

3. Teacher - performs testing routin: introduces to what a student should do, asks questions, checks answers

4. Teacher needs helpp of ScannerWrapper and LocalesRepository to read answers and translate, if necessary

This is mostly it ;)