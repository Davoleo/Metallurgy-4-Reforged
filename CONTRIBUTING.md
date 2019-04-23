# Contributing to the "Metallurgy 4: Reforged" project

##### Prerequisites
1.  _Git and Github Knowledge (This guide **does not** explain how git works)_

---
## Guide on Issues
-   Check if there's another open issue about the same topic (or else you'll be working for nothing cause we'll likely close your thread as duplicate)
-   Be sure to be as specific as possible when describing the issue
-   Provide the most information you are able to (crash reports | logs | mod versions | forge version)
-   If possible post screenshots of the issue you're experiencing
-   _if your issue does not respect these contributing lines it will be likely closed as invalid_

---
## Guide on Pull Requests

Before contributing actively to the repository think of what you are going to add and why it makes sense to add it!
Tell us about it too!

_Steps:_
1. Fork the repository
2. Ask the dev in what branch you should implement your feature
3. Push the feature on your fork of the repository
4. Create the pull request on the original repository
5. Follow your pull request review and discussion
6. Enjoy your merged commits =)

---

#### Specific information about steps previously listed

###### Fork the repository
![Forking the repo](https://i.imgur.com/8PV2hlZ.png?1)

---
To contact us about the 2° step join this [discord server](https://discord.gg/yDumVrs)

---
###### Pushing your features to your fork
Clone the repository on your local machine and push features from a git client<br>
I highly advise GitKraken as a git client (Easy to use and intuitive)<br>
[Tutorial](https://support.gitkraken.com)<br>
[Download](https://www.gitkraken.com/invite/mVRqih9P)

If you want to contribute be sure to add Code Comments AND JavaDoc comments for methods<br>
Example:
```java
public class ExampleClass {
    //Example BlockPos variable
    private BlockPos exampleVar;
    
    /**
    * This method initializes all the BlockPos Variables
    */
    @Override
    public static void initValues()
    {
        exampleVar.init();   
    } 
}
```

**Please pay attention on where you upload your features** <br>
you should know how a mod is structured<br>
Source code directory: `src/main/java`<br>
Resources directory: `src/main/resources`<br><br>

**Refer to the model/blockstate directory for texture organization and naming conventions** <br>
Models directory: `src/main/resources/assets/m5/models` <br>
Blockstates directory: `src/main/resources/assets/m5/blockstates` <br>

---
###### Creating the Pull request
Pull Request Check-in!<br>
**BEFORE creating the pull request check if your changes break anything and if your PR has any bugs**<br>
Tell us about the changes and how they are implemented in the pull request description<br>
If you pushed commit to one branch you need to pull request to that same branch!<br>
Compare across forks!<br>
Check in the commit section below if the commits you are proposing are yours

Guide: <br>
![Creating the Pull Request](https://i.imgur.com/0IWdRnA.gif)

---
###### Pull request discussion
Remember to always keep an eye on the pull request discussion, because we might ask you to change something in the files or ask you something about the pull request content

---
_**(if your pull request does not respect these contributing lines it will be likely closed as invalid)**_
