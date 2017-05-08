# jknn
A Java implementation of the k-nearest neighbors algorithm

# Example
```java
// Create the classifier with the specified distance function
Classifier classifier = new BruteForceClassifier(new EuclideanDistance());

// Fit the classifier with the specified features and labels
classifier.fit(features, labels);

// Classify a new feature set with the specified k value
classifier.classify(features, 3);
```
