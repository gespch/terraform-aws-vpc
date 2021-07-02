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
        path="dso_iac_cloudbees"
        working_directory = "cloudbees"
        file_name_env   = "terraform.tfvars.json"
    }
 
    terraform {
        enforce_deploy   = false
        working_directory = "cloudbees"   
        file_name_env   = "terraform.tfvars.json"
     secrets{
           someTextCredential{
           type = "text"
           name = "VARIABLE_NAME"
           id = "jenkins_sonar"
           }
        }
    }
 
   
}
 
application_environments {
    dev{
        aws {
            credentials_id = '<credentials>'
            region  = 'us-east-2'
            stage   = 'DESA'
        }
    }
    test{
        aws {
            credentials_id = '<credentials>'
            region  = 'us-east-2'
            stage   = 'TEST'
        }
    }
    prod{
        aws { 
            region  = 'us-east-1'
            stage   = 'PROD'
            profile = 'prod-profile'
        }
    }
}
