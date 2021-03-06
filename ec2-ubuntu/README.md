# EC2 Ubuntu Cloud Driver  

This cloud driver uses the default provisionig driver based on JClouds, but start Ubuntu images on EC2 and then adds the `ubuntu` user to the list of sudoers. 

> This cloud driver has been tested with Cloudify 2.1.1 m2 build available for download [here](http://repository.cloudifysource.org/org/cloudifysource/2.1.1/gigaspaces-cloudify-2.1.1-ga-b1396-214.zip)

# Installation 

To install this driver following the following steps (make sure you have git client installed): 

* Clone the repo and copy the cloud driver folder to the right location in the cloudify distro: 
<pre><code>
git clone git@github.com:CloudifySource/cloudify-cloud-drivers.git
cp -r ec2-ubuntu/ <cloudify root>/tools/cli/plugins/esc
</code></pre>

* Edit the file `ec2-ubuntu-cloud.groovy` and add your cloud credentials instead of the place holders
<pre><code>
user {
	 // Optional. Identity used to access cloud. 
	 // When used with the default driver, maps to the identity used to create the ComputeServiceContext.
	 user "ENTER_USER"
	 
	 // Optional. Key used to access cloud.
	 // When used with the default driver, maps to the credential used to create the ComputeServiceContext.
	 apiKey "ENTER_KEY"
		 
	 keyFile "ENTER_KEY_FILE_NAME"
}
</code></pre>

* Place your key file (.pem file) in the upload directory of the cloud driver and update the relevant locations in the `ec2-ubuntu-cloud.groovy` file with the key file's name.

* Bootstrap the cloud: 
<pre><code>
cd <cloudify root>/bin
./cloudify.sh
</code></pre>

<pre><code>

  .oooooo.   oooo                              .o8   o8o   .o88o.             
 d8P'  `Y8b  `888                             "888   `"'   888 `"             
888           888   .ooooo.  oooo  oooo   .oooo888  oooo  o888oo  oooo    ooo 
888           888  d88' `88b `888  `888  d88' `888  `888   888     `88.  .8'  
888           888  888   888  888   888  888   888   888   888      `88..8'   
`88b    ooo   888  888   888  888   888  888   888   888   888       `888'    
 `Y8bood8P'  o888o `Y8bod8P'  `V88V"V8P' `Y8bod88P" o888o o888o       .8'     
                                                                  .o..P'      
                                                                  `Y8P'

  GigaSpaces Cloudify Shell.  

  Note for Windows Users:
   The Cloudify shell does not currently support the back-slash character ('\')
   as file separator. Instead, use the forward-slash character ('/') when
   specifying file paths.

Hit '<tab>' for a list of available commands.
Hit '[cmd] --help' for help on a specific command.
Hit '<ctrl-d>' or 'exit' to exit the console.

Cloudify version: 2.1.0


cloudify@default> bootstrap-cloud -timeout 20 ec2-ubuntu
</code></pre>

If you need more detailed directions you can refer to the [EC2 setup instructions](http://www.cloudifysource.org/guide/setup/configuring_ec2) in the cloudify documentation

