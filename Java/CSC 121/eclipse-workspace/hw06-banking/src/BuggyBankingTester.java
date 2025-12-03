import static org.junit.Assert.*;
import org.junit.Test;

public class BuggyBankingTester {
  buggy.IAccount as = new buggy.Checking("Adam Smith", 123, 150, 50);
  buggy.IAccount bj = new buggy.Savings("Betty Jones", 456, 120, 2.5);
  buggy.IAccount pt1 = new buggy.CD("Pat Malloy", 334, 300, false);
  buggy.IAccount pt2 = new buggy.CD("Pat Malloy", 334, 300, true);

  @Test
  public void testAmtAvailable() {
      assertEquals(100, this.as.amtAvailable(), 0);
      assertEquals(120, this.bj.amtAvailable(), 0);
      //assertEquals(0, this.pt1.amtAvailable(), 0);  
      assertEquals(300, this.pt2.amtAvailable(), 0); 
      /*
       *  The bug is in the amtAvailable method in the CD class 
       *  CD accounts must be mature before any funds can be withdrawn. pt1 is not fully
       *   matured (denoted by the false parameter for the boolean mature), and therefore should not 
       *   have any funds available to withdraw. Slop E. Koder likely did not include an if/else
       *   condition in the method body to check if the CD account was matured or not
       */
  }
  
  @Test
  public void testMoreAvailable() {
	  assertEquals(true, this.bj.moreAvailable(as));
      assertEquals(false, this.as.moreAvailable(bj));
      assertEquals(false, this.pt1.moreAvailable(bj));  
      assertEquals(true, this.pt2.moreAvailable(pt1)); 
      /*
       * The bug is in the moreAvailable method, and it is likely present in all 3 classes
       * It seems that Slop E. Koder checked whether or not the current balance of one account was
       *  higher than another, instead of the amount available to withdraw. For example, IAccount as
       *  has a higher current balance than IAccount bj, but because as has a minimum balance of 50,
       *  it only has $100 available to withdraw, which is less than bj's 120. Slop E. Koder likely
       *  did not use the amtAvailable() method when designing the moreAvailable() method, which 
       *  caused this issue
       *  
       *  I think there is an issue in the moreAvailable() method in the Checking class that
       *   does not use amtAvailable() to find the actual amount available to withdraw, but instead 
       *   simply uses the account's current balance
       *   
       *  I think there is an issue in the moreAvailable() method in the CD class that does not 
       *   check whether the account is matured or not to find its amount available to withdraw, but
       *   instead simply uses the account's current balance
       */
  }

  @Test
  public void testWithdraw() {
	  assertEquals(as, this.as.withdraw(125));
	  assertEquals(new buggy.Checking("Adam Smith", 123, 145, 50), this.as.withdraw(5));
  }

  /*
   * I am not sure where the bugs are in the withdraw() method. When attempting to withdraw more than
   *  the account has available, it does not return the original account like it should, and it also
   *  does not return an account with an updated balance when attempting to withdraw an amount that
   *  can be withdrawn
   */
}

