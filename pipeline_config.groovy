pipeline_template   = "template_default"
 
libraries {
    npm { 
        directories_install = []
        directories_build = []
    }
 
    sonarqube {
        //Para el pipeline si no pasa el quality gate del Sonar.
        stopQgError = true
    }
    
   vault{
        path="dso_iac_example"
        working_directory = "examples/simple-vpc"
        file_name_env   = "terraform.tfvars.json"
    }
 
    terraform {
        enforce_deploy   = false
        working_directory = "examples/simple-vpc"   
        file_name_env   = "terraform.tfvars.json"
    }
 
   
}
 
application_environments {
       dev{
        terraform {
            secrets{
               someUsernamePasswordCredential{
                    type = "usernamePassword"
                    usernameVar = "USER"
                    passwordVar = "PASS"
                    id = "giovanni_expinoza_aws_personal"
                }
            }
        }
    }
    test{
       
    }
    prod{

    }
}
