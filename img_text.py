# -*- coding: utf-8 -*-
"""
Created on Sat Mar  2 12:20:15 2019

@author: admin
"""

import pytesseract
from PIL import Image
#from wand.image import Image as wi

#pdf = wi(filename = "sample2.pdf", resolution = 300)
#pdfImage = pdf.convert('jpeg')
im1 = Image.open("C:/Users/admin/Desktop/Hackathon/img1.jpeg")

im = Image.open("C:/Users/admin/Desktop/Hackathon/3126_jpg.jpg")

text1 = pytesseract.image_to_string(im1, lang = 'eng')
text = pytesseract.image_to_string(im, lang = 'eng')

print(text1)