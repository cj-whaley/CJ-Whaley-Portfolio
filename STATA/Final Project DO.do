clear all
cd "/Users/cjwhaley/Desktop/ECO 420"
use "Baseball Salary, Final-1.dta", clear
capture log close
log using "Baseball Project.log", replace

// Sample Selection
keep if Pos=="SP" | Pos=="RP" //keep only pitchers
drop if FB_Velo_avg== 0 // keep only pitchers with avg velo data
drop if AAV==0 // keep only pitchers who signed a deal

// Generate market size dummy
gen team=""
replace team=PrevTeam if SigningTeam==""
replace team=SigningTeam if SigningTeam!=""
gen large_market=0
replace large_market=1 if team=="LAD" | team=="LAA" | team=="NYY" | team=="NYM" | team=="BOS" | team=="CHC" | team=="CHW" | team=="PHI"
// sets large market teams as LAD, LAA, NYY, NYM, BOS, CHC, CHW, and PHI
// all other teams take a large_market value of 0, meaning they are a small market teams

// generate log transformed AAV
gen logAAV=log(AAV)

// Throwing Hand dummy for LHP
gen lefty=0
replace lefty=1 if Thr=="L"

// SP dummy
gen SP=0
replace SP=1 if Pos=="SP"

// using an interaction term for average FB velo with SP and lefty combined

// Regression
reg logAAV c.FB_Velo_avg##i.lefty##i.SP Age careerWAR FIP large_market, robust
estat vif

// send results to word document
outreg2 using "Final_Project_Regression.doc", replace word

// Generating summary statistics
sum(AAV FB_Velo_avg lefty SP Age careerWAR FIP large_market)
outreg2 using "Final_Project_Summary.doc", replace sum(log)

// Generate variable to display AAV in millions for mean data
gen AAV_m = AAV / 1000000

// Display mean AAV (in millions of dollars) by year for SP
tabstat AAV_m if SP == 1, by(year) stat(mean) columns(statistics)

// Display mean AAV (in millions of dollars) by year for RP
tabstat AAV_m if SP == 0, by(year) stat(mean) columns(statistics)

// Display mean Average FB velo (in mph) by year for SP
tabstat FB_Velo_avg if SP == 1, by(year) stat(mean) columns(statistics)

// Display mean Average FB velo (in mph) by year for RP
tabstat FB_Velo_avg if SP == 0, by(year) stat(mean) columns(statistics)
