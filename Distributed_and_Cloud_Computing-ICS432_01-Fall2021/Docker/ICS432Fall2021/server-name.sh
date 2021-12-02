#! /bin/bash
vm_hostname="$(curl -H "Metadata-Flavor:Google" \
http://169.254.169.254/computeMetadata/v1/instance/name)"
echo "Page served from: $vm_hostname" | \
sudo tee /data/www/ICS432Fall2021/server-ip.html
