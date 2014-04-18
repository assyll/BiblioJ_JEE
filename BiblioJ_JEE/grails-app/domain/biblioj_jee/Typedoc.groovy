package biblioj_jee

class Typedoc {
	String intitule

    static constraints = {
		intitule blank : false, nullable : false
    }
	
	String toString(){
		intitule
	}
}
